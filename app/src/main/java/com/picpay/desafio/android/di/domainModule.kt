package com.picpay.desafio.android.di

import com.picpay.desafio.android.domain.interactor.GetUsers
import com.picpay.desafio.android.domain.util.ThreadContextProvider
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {
    single { ThreadContextProvider() }
    factory { (scope: CoroutineScope) -> GetUsers(repository = get(), scope = scope) }
}