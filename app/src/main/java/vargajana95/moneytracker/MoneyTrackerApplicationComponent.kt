package vargajana95.moneytracker

import dagger.Component
import vargajana95.moneytracker.ui.MainActivity
import vargajana95.moneytracker.ui.UIModule
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class])
interface MoneyTrackerApplicationComponent {
    fun inject(mainActivity: MainActivity)
    //fun inject(artistsFragment: ArtistsFragment)
}