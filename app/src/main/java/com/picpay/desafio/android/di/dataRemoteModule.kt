package com.picpay.desafio.android.di

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.remote.datasource.UserDataSourceImpl
import com.picpay.desafio.android.data.remote.service.PicPayService
import com.picpay.desafio.android.data.remote.util.PIC_PAY_API_URL
import com.picpay.desafio.android.data.remote.util.WebServiceFactory
import com.picpay.desafio.android.data.repository.datasource.UserDataSource
import org.koin.dsl.module

val dataRemoteModule = module {

    single<PicPayService> {
        WebServiceFactory.createWebService(
            WebServiceFactory.defaultBuilder(BuildConfig.DEBUG && BuildConfig.BUILD_TYPE == "debug"),
            url = PIC_PAY_API_URL
        )
    }

    single<UserDataSource> { UserDataSourceImpl(get()) }

}