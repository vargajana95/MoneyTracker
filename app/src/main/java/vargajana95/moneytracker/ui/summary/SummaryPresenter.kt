package vargajana95.moneytracker.ui.transactions

import android.util.Log
import io.reactivex.disposables.Disposable
import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.model.Transaction
import vargajana95.moneytracker.ui.Presenter
import kotlin.math.absoluteValue

class SummaryPresenter(
    private val transactionInteractor: TransactionInteractor
) : Presenter<SummaryScreen>() {

    private var transactionSubscription: Disposable? = null

    override fun attachScreen(screen: SummaryScreen) {
        super.attachScreen(screen)

        transactionSubscription = transactionInteractor.fetchedTransactions.subscribe {
            showBudget(it)
        }
    }

    private fun showBudget(transactions: List<Transaction>) {
        val income = transactions.filter { t->t.amount>0 }.sumBy {  t->t.amount}
        val expense = transactions.filter { t->t.amount<0 }.sumBy {  t->t.amount}.absoluteValue
        val budget = transactions.sumBy {  t->t.amount}

        screen?.showBudget(expense, income, budget)
    }

    override fun detachScreen() {
        super.detachScreen()

       transactionSubscription?.dispose()
    }
}