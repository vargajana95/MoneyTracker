package vargajana95.moneytracker.test

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction
import vargajana95.moneytracker.ui.transactions.TransactionsPresenter
import vargajana95.moneytracker.ui.transactions.TransactionsScreen

@RunWith(RobolectricTestRunner::class)
class TransactionsPresenterTest {
    lateinit var transactionsPresenter: TransactionsPresenter
    lateinit var transactionsScreen: TransactionsScreen
    lateinit var transactionsInteractor: TransactionInteractor

    lateinit var transaction: Transaction

    @Before
    fun setup() {
        transactionsInteractor = mock()

        whenever(transactionsInteractor.fetchedTransactions).thenReturn( BehaviorSubject.create<List<Transaction>>())

        transactionsScreen = mock()
        transactionsPresenter = TransactionsPresenter(transactionsInteractor)
        transactionsPresenter.attachScreen(transactionsScreen)

        transaction = Transaction("0", "2020-04-12", 1000, "TestMemo", "TestName", Category("1", "TestCategory"))
    }

    @Test
    fun testRefreshTransactions() {
        transactionsPresenter.refreshTransactions()

        verify(transactionsInteractor, times(1)).fetchTransactions()
    }

    @Test
    fun testCreateNewTransactionDialog() {
        val category = Category("0", "TestCategory")
        whenever(transactionsInteractor.getCategories()).thenReturn(Single.just(listOf(category)))

        transactionsPresenter.createNewTransactionDialog()

        val list = argumentCaptor<MutableList<Category>>()
        verify(transactionsScreen, times(1)).showNewTransactionDialog(list.capture())
        assert(list.firstValue.size == 1)
        assert(category in list.firstValue)
    }

    @Test
    fun testCreateNewTranscation() {
        whenever(transactionsInteractor.createNewTransaction(any())).thenReturn(Single.just(transaction))

        transactionsPresenter.createNewTransaction(transaction)

        verify(transactionsInteractor, times(1)).createNewTransaction(transaction)
        verify(transactionsScreen, times(1)).transactionAdded(transaction)
    }

    @After
    fun tearDown() {
        transactionsPresenter.detachScreen()
    }


}