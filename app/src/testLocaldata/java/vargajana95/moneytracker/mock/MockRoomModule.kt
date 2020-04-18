package vargajana95.moneytracker.mock

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import vargajana95.moneytracker.network.YnabApi
import vargajana95.moneytracker.persistence.dao.CategoryDAO
import vargajana95.moneytracker.persistence.dao.TransactionDAO
import javax.inject.Singleton

@Module
class MockRoomModule {

    @Provides
    @Singleton
    fun provideTransactionDao(): TransactionDAO = MockTransactionDAO()

    @Provides
    @Singleton
    fun provideCategoryDao(): CategoryDAO = MockCategoryDAO()

}