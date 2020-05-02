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
import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.mock.MockCategoryDAO
import vargajana95.moneytracker.mock.MockTransactionDAO
import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction
import vargajana95.moneytracker.persistence.TransactionData
import vargajana95.moneytracker.persistence.dao.CategoryDAO
import vargajana95.moneytracker.persistence.dao.TransactionDAO

@RunWith(RobolectricTestRunner::class)
class TransactionInteractorTest {

    lateinit var categoryDAO: CategoryDAO
    lateinit var transactionDAO: TransactionDAO
    lateinit var transactionInteractor: TransactionInteractor

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        transactionDAO = spy(MockTransactionDAO())
        categoryDAO = spy(MockCategoryDAO())
        transactionInteractor = TransactionInteractor(transactionDAO, categoryDAO)
    }


    @Test
    fun testFetchTransactions() {
        val testObserver = TestObserver<List<Transaction>>()
        transactionInteractor.fetchedTransactions.subscribe(testObserver)
        transactionInteractor.fetchTransactions()

        verify(transactionDAO, times(1)).getAllTransactions()

        testObserver.assertNoErrors()
        assert(testObserver.values()[0].any{t -> t.name == "Test3"})
    }

    @Test
    fun createNewTransaction() {
        val transaction = Transaction("0", "2020-04-12", 1000, "TestMemo", "TestName", Category("1", "TestCategory"))


        val testObserver = TestObserver<Transaction>()
        transactionInteractor.createNewTransaction(transaction).subscribe(testObserver)

        val newTransaction = argumentCaptor<TransactionData>()
        verify(transactionDAO, times(1)).insertTransactions(newTransaction.capture())

        assert(newTransaction.firstValue.name == transaction.name)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        assert(testObserver.values()[0].name == transaction.name)
    }

    @Test
    fun getCategories() {
        val testObserver = TestObserver<List<Category>>()
        transactionInteractor.getCategories().subscribe(testObserver)

        verify(categoryDAO, times(1)).getAllCategories()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        assert(testObserver.values()[0].any{ it.name == "Category 1"})
    }

    @After
    fun tearDown() {
    }
}