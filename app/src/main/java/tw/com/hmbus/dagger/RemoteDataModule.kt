package tw.com.hmbus.dagger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tw.com.hmbus.AuthFactory
import tw.com.hmbus.data.remote.PtxService
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @LoggingInterceptor
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @RequestInterceptor
    @Provides
    @Singleton
    fun provideRequestInterceptor(): Interceptor = Interceptor { chain ->
        val signature = AuthFactory.createPtxAuth()
        val request = chain.request()

        val urlBuilder = request.url.newBuilder()
            .addQueryParameter("\$format", "JSON")

        val requestBuilder = request.newBuilder()
            .url(urlBuilder.build())
            .addHeader("Authorization", signature.auth)
            .addHeader("x-date", signature.xDate)

        chain.proceed(requestBuilder.build())
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        @LoggingInterceptor loggingInterceptor: Interceptor,
        @RequestInterceptor requestInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient().newBuilder()
            .authenticator { route, response ->
                response.request
            }
            .addInterceptor(loggingInterceptor)
            .addInterceptor(requestInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://ptx.transportdata.tw/MOTC/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePtxService(retrofit: Retrofit): PtxService = retrofit.create(PtxService::class.java)
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RequestInterceptor
