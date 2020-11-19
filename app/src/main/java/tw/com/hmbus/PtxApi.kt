package tw.com.hmbus

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface PtxApi {
    companion object {
        val service = Retrofit.Builder()
            .baseUrl("https://ptx.transportdata.tw/MOTC/")
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(RequestInterceptor())
                    .build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PtxApi::class.java)
    }
}

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val signature = SignatureFactory.generatePtxSignature()
        val request = chain.request()

        val urlBuilder = request.url.newBuilder()
            .addQueryParameter("\$format", "JSON")

        val requestBuilder = request.newBuilder()
            .url(urlBuilder.build())
            .addHeader("Authorization", signature.auth)
            .addHeader("x-date", signature.xDate)

        return chain.proceed(requestBuilder.build())
    }
}
