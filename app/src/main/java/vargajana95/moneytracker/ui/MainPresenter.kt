package vargajana95.moneytracker.ui

class MainPresenter : Presenter<MainScreen>() {

    fun show() {
        screen?.show("Hello")
    }
}