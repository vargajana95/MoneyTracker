package vargajana95.moneytracker.interactor

import dagger.Module
import dagger.Provides
import vargajana95.moneytracker.interactor.TransactionInteractor
import vargajana95.moneytracker.network.YnabApi
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideTransactionsInteractor(ynabApi: YnabApi) = TransactionInteractor(ynabApi)
}