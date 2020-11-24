package tw.com.hmbus.dagger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tw.com.hmbus.SignatureFactory
import tw.com.hmbus.data.remote.PtxApi
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RemoteDataModule {

    @Provides
    fun provideRequestInterceptor(): Interceptor = Interceptor { chain ->
        val signature = SignatureFactory.generatePtxSignature()
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
    fun provideOkHttp(requestInterceptor: Interceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(requestInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): PtxApi =
        Retrofit.Builder()
            .baseUrl("https://ptx.transportdata.tw/MOTC/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PtxApi::class.java)
}
