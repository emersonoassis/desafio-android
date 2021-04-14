package com.picpay.desafio.android.data.remote.util

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object WebServiceFactory {
    inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(PIC_PAY_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create()
    }

    fun okHttpClientBuilder(
        cache: Cache,
        wasDebugVersion: Boolean
    ) = OkHttpClient.Builder()
        .addNetworkInterceptor(CacheInterceptor())
        .cache(cache)
        .httpLoggingInterceptor(wasDebugVersion)
        .build()


    private fun OkHttpClient.Builder.httpLoggingInterceptor(wasDebugVersion: Boolean) =
        when (wasDebugVersion) {
            true -> {
                val interceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(interceptor)
            }
            else -> this
        }
}