package vargajana95.moneytracker.ui.transactions

import vargajana95.moneytracker.ui.Presenter

class SummaryPresenter : Presenter<SummaryScreen>() {

    fun show() {
        screen?.show("Hello")
    }
}