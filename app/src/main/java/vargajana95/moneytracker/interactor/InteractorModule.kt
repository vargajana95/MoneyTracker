package vargajana95.moneytracker.interactor

import dagger.Module
import dagger.Provides
import vargajana95.moneytracker.interactor.TransactionInteractor
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideTransactionsInteractor() = TransactionInteractor()
}