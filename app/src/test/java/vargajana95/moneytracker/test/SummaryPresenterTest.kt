package vargajana95.moneytracker.test

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.subjects.BehaviorSubject
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction
import vargajana95.moneytracker.ui.summary.SummaryPresenter
import vargajana95.moneytracker.ui.summary.SummaryScreen

@RunWith(RobolectricTestRunner::class)
class SummaryPresenterTest {

    lateinit var summaryPresenter: SummaryPresenter
    lateinit var summaryScreen: SummaryScreen
    lateinit var transactionsInteractor: TransactionInteractor

    lateinit var fetchedTransactions: BehaviorSubject<List<Transaction>>

    @Before
    fun setup() {
        transactionsInteractor = mock()

        fetchedTransactions = BehaviorSubject.create<List<Transaction>>()
        whenever(transactionsInteractor.fetchedTransactions).thenReturn(fetchedTransactions)

        summaryScreen = mock()
        summaryPresenter = SummaryPresenter(transactionsInteractor)
        summaryPresenter.attachScreen(summaryScreen)



    }

    @Test
    fun testShowBudget() {
        val transactions = listOf(
            Transaction("0", "2020-04-12", 1000, "TestMemo1", "TestName1", Category("1", "TestCategory")),
            Transaction("1", "2020-04-12", -500, "TestMemo2", "TestName2", Category("1", "TestCategory")),
            Transaction("2", "2020-04-12", 2000, "TestMemo3", "TestName3", Category("1", "TestCategory")))
        fetchedTransactions.onNext(transactions)

        verify(summaryScreen, times(1)).showBudget(500, 3000, 2500)
    }


    @After
    fun tearDown() {
        summaryPresenter.detachScreen()
    }
}