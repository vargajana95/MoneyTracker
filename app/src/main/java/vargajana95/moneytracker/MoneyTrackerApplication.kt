package vargajana95.moneytracker

import android.app.Application
import vargajana95.moneytracker.ui.UIModule

class MoneyTrackerApplication : Application() {
    lateinit var injector: MoneyTrackerApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerMoneyTrackerApplicationComponent.builder().uIModule(UIModule(this)).build()
    }
}