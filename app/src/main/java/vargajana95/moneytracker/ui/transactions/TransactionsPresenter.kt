package vargajana95.moneytracker.ui.transactions

import io.reactivex.disposables.CompositeDisposable
import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction
import vargajana95.moneytracker.ui.Presenter
import javax.inject.Inject

class TransactionsPresenter @Inject constructor(val transactionInteractor: TransactionInteractor) :
    Presenter<TransactionsScreen>() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun refreshTransactions() {
        val s = transactionInteractor.getTransactions()
            .subscribe { transactions: List<Transaction> ->
                screen?.showTransactions(transactions)
            }

        compositeDisposable.add(s)
    }

    fun createNewTransaction(transaction: Transaction) {
        compositeDisposable.add(transactionInteractor.createNewTransaction(transaction).subscribe{
            t: Transaction->
            screen?.transactionAdded(t)
        })
    }

    fun createNewTransactionDialog() {
        compositeDisposable.add(transactionInteractor.getCategories().subscribe{
                categories: List<Category>->
            screen?.showNewTransactionDialog(categories)
        })
    }

    override fun detachScreen() {
        super.detachScreen()

        compositeDisposable.clear()
    }

}