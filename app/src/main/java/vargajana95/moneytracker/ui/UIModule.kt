package vargajana95.moneytracker.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import vargajana95.moneytracker.ui.transactions.SummaryPresenter
import vargajana95.moneytracker.ui.transactions.TransactionsPresenter
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun summaryPresenter() =
        SummaryPresenter()

    @Provides
    @Singleton
    fun transactionsPresenter() =
        TransactionsPresenter()

}