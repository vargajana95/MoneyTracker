package vargajana95.moneytracker

import android.app.Activity
import androidx.fragment.app.Fragment

val Activity.injector: MoneyTrackerApplicationComponent
    get() {
        return (this.applicationContext as MoneyTrackerApplication).injector
    }

val Fragment.injector: MoneyTrackerApplicationComponent
    get() {
        return (this.requireContext().applicationContext as MoneyTrackerApplication).injector
    }