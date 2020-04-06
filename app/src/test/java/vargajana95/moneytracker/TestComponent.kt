package vargajana95.moneytracker

import dagger.Component
import vargajana95.moneytracker.interactor.InteractorModule
import vargajana95.moneytracker.mock.MockNetworkModule
import vargajana95.moneytracker.test.TransactionsTest
import javax.inject.Singleton

@Singleton
@Component(modules = [MockNetworkModule::class, TestModule::class, InteractorModule::class])
interface TestComponent : MoneyTrackerApplicationComponent {
    fun inject(transactionsTest: TransactionsTest)
}