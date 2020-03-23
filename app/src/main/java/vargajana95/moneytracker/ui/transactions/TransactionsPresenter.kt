package vargajana95.moneytracker.ui.transactions

import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.ui.Presenter
import javax.inject.Inject

class TransactionsPresenter @Inject constructor(val transactionInteractor: TransactionInteractor): Presenter<SummaryScreen>() {

    fun show() {
        screen?.show("Hello")
    }
}