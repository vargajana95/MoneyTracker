package vargajana95.moneytracker.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.ui.transactions.SummaryPresenter
import vargajana95.moneytracker.ui.transactions.TransactionsPresenter
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun summaryPresenter(transactionInteractor: TransactionInteractor) =
        SummaryPresenter(transactionInteractor)

    @Provides
    @Singleton
    fun transactionsPresenter(transactionInteractor: TransactionInteractor) =
        TransactionsPresenter(transactionInteractor)

}