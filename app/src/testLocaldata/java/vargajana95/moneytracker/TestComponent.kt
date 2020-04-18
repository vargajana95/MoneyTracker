package vargajana95.moneytracker

import dagger.Component
import vargajana95.moneytracker.interactor.InteractorModule
import vargajana95.moneytracker.mock.MockRoomModule
import javax.inject.Singleton

@Singleton
@Component(modules = [MockRoomModule::class, TestModule::class, InteractorModule::class])
interface TestComponent2 : MoneyTrackerApplicationComponent {
    //fun inject(transactionsTest: TransactionsTest)
}