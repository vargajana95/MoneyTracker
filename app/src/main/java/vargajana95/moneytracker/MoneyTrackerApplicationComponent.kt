package vargajana95.moneytracker

import dagger.Component
import vargajana95.moneytracker.interactor.InteractorModule
import vargajana95.moneytracker.ui.UIModule
import vargajana95.moneytracker.ui.summary.SummaryFragment
import vargajana95.moneytracker.ui.transactions.TransactionListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, InteractorModule::class])
interface MoneyTrackerApplicationComponent {
    fun inject(transactionListFragment: TransactionListFragment)
    fun inject(summaryFragment: SummaryFragment)
}