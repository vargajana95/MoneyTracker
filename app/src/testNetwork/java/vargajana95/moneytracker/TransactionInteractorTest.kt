package vargajana95.moneytracker

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import vargajana95.moneytracker.dto.CreateTransactionRequestData
import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.mock.MockYnabApi
import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction
import vargajana95.moneytracker.network.YnabApi

@RunWith(RobolectricTestRunner::class)
class TransactionInteractorTest {

    lateinit var ynabiApi: YnabApi
    lateinit var transactionInteractor: TransactionInteractor

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        ynabiApi = spy(MockYnabApi())
        transactionInteractor = TransactionInteractor(ynabiApi)
    }


    @Test
    fun testFetchTransactions() {
        val testObserver = TestObserver<List<Transaction>>()
        transactionInteractor.fetchedTransactions.subscribe(testObserver)
        transactionInteractor.fetchTransactions()

        verify(ynabiApi, times(1)).getTransactions()

        testObserver.assertNoErrors()
        assert(testObserver.values()[0].any{t -> t.name == "Test3"})
    }

    @Test
    fun createNewTransaction() {
        val transaction = Transaction("0", "2020-04-12", 1000, "TestMemo", "TestName", Category("1", "TestCategory"))


        val testObserver = TestObserver<Transaction>()
        transactionInteractor.createNewTransaction(transaction).subscribe(testObserver)

        val newTransaction = argumentCaptor<CreateTransactionRequestData>()
        verify(ynabiApi, times(1)).createTransaction(newTransaction.capture())

        assert(newTransaction.firstValue.transaction.name == transaction.name)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        assert(testObserver.values()[0].name == transaction.name)
    }

    @Test
    fun getCategories() {
        val testObserver = TestObserver<List<Category>>()
        transactionInteractor.getCategories().subscribe(testObserver)

        verify(ynabiApi, times(1)).getCategories()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        assert(testObserver.values()[0].any{ it.name == "Cat1"})
    }

    @After
    fun tearDown() {
    }
}