package vargajana95.moneytracker.ui.transactions

import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.model.TransactionResult
import vargajana95.moneytracker.ui.Presenter
import javax.inject.Inject

class TransactionsPresenter @Inject constructor(val transactionInteractor: TransactionInteractor): Presenter<TransactionsScreen>() {

    fun refreshTransactions() {
        transactionInteractor.getTransactions()
            .subscribe {
                    transactions: List<TransactionResult> -> screen?.showTransactions(transactions)
            }


    }
}