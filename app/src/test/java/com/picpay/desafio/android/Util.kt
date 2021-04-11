package com.picpay.desafio.android

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.ThreadLocalRandom

fun <R> Flow<R>.testFlow(test: R.() -> Unit) {
    runBlocking {
        collect {
            it.test()
        }
    }
}

fun randomInt() = ThreadLocalRandom.current().nextInt(0, 1000 + 1)

fun randomString() = UUID.randomUUID().toString()