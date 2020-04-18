package vargajana95.moneytracker.ui.transactions

import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.dto.TransactionResult
import vargajana95.moneytracker.model.Transaction
import vargajana95.moneytracker.ui.Presenter
import javax.inject.Inject

class TransactionsPresenter @Inject constructor(val transactionInteractor: TransactionInteractor): Presenter<TransactionsScreen>() {

    fun refreshTransactions() {
        transactionInteractor.getTransactions()
            .subscribe {
                    transactions: List<Transaction> -> screen?.showTransactions(transactions)
            }


    }
}