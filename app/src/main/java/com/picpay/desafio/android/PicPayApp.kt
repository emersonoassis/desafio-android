package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.dataRemoteModule
import com.picpay.desafio.android.di.domainModule
import com.picpay.desafio.android.di.repositoryModule
import com.picpay.desafio.android.di.viewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class PicPayApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(dataRemoteModule, repositoryModule, domainModule, viewModelModule))
        }
    }
}