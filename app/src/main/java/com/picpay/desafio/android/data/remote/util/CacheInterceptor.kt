package com.picpay.desafio.android.data.remote.util

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxAge(5, TimeUnit.MINUTES)
            .build()

        return response.newBuilder()
            .removeHeader("Pragma")
            .removeHeader("Cache-control")
            .header("Cache-control", cacheControl.toString())
            .build()
    }
}