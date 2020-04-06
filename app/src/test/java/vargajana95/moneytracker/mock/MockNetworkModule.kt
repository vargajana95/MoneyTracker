package vargajana95.moneytracker.mock

import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vargajana95.moneytracker.network.NetworkConfig
import vargajana95.moneytracker.network.YnabApi
import javax.inject.Singleton

@Module
class MockNetworkModule {

    @Provides
    @Singleton
    fun provideYnabApi(client: OkHttpClient): YnabApi = MockYnabApi()

}