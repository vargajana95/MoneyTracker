package vargajana95.moneytracker

import android.content.Context
import dagger.Module
import dagger.Provides
import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.ui.transactions.SummaryPresenter
import vargajana95.moneytracker.ui.transactions.TransactionsPresenter
import javax.inject.Singleton

@Module
class TestModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun summaryPresenter() =
        SummaryPresenter()

    @Provides
    @Singleton
    fun transactionsPresenter(transactionInteractor: TransactionInteractor) =
        TransactionsPresenter(transactionInteractor)
}