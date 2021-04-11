package com.picpay.desafio.android.data.remote.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object WebServiceFactory {
    inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient,
        url: String
    ): T {

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create()
    }

    fun defaultBuilder(
        wasDebugVersion: Boolean = false
    ) = OkHttpClient.Builder()
        .cache(null)
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