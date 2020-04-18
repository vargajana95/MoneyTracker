package vargajana95.moneytracker.mock

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import vargajana95.moneytracker.network.YnabApi
import javax.inject.Singleton

@Module
class MockNetworkModule {

    @Provides
    @Singleton
    fun provideYnabApi(): YnabApi = MockYnabApi()

}