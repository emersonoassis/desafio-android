package com.picpay.desafio.android.di

import android.content.Context
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.remote.datasource.UserDataSourceImpl
import com.picpay.desafio.android.data.remote.service.PicPayService
import com.picpay.desafio.android.data.remote.util.CACHE_KEY
import com.picpay.desafio.android.data.remote.util.CACHE_MAX_SIZE
import com.picpay.desafio.android.data.remote.util.WebServiceFactory
import com.picpay.desafio.android.data.repository.datasource.UserDataSource
import okhttp3.Cache
import org.koin.dsl.module
import java.io.File

val dataRemoteModule = module {

    single { Cache(File(get<Context>().cacheDir, CACHE_KEY), CACHE_MAX_SIZE) }

    single<PicPayService> {
        WebServiceFactory.createWebService(
            WebServiceFactory.okHttpClientBuilder(
                cache = get(),
                wasDebugVersion = BuildConfig.DEBUG && BuildConfig.BUILD_TYPE == "debug"
            )
        )
    }

    single<UserDataSource> { UserDataSourceImpl(get()) }

}