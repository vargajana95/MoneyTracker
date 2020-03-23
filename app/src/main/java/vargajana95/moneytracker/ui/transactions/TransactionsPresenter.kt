package vargajana95.moneytracker.ui.transactions

import vargajana95.moneytracker.ui.Presenter

class TransactionsPresenter : Presenter<SummaryScreen>() {

    fun show() {
        screen?.show("Hello")
    }
}