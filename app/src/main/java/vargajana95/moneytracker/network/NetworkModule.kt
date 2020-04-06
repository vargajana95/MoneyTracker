package vargajana95.moneytracker.network

import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor { chain ->
                val original: Request = chain.request()

                val request: Request = original.newBuilder()
                    .header("Authorization", "Bearer d2ca7ab5df9d4f5966147341f039a49ad0fd929679fee849f3ed9ff28cae229f")
                    .method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }
            .build()
    }


    @Provides
    @Singleton
    fun provideYnabApi(client: OkHttpClient): YnabApi {
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(NetworkConfig.API_ENDPOINT_ADDRESS)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

            .build()
        return retrofit.create(YnabApi::class.java)
    }

}