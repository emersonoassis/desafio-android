package com.picpay.desafio.android.domain.interactor

import com.picpay.desafio.android.domain.util.ThreadContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class UseCase<T>(private val scope: CoroutineScope) : KoinComponent {
    private val contextProvider: ThreadContextProvider by inject()

    abstract fun run(): Flow<T>

    operator fun invoke(
        onError: ((Throwable) -> Unit) = {},
        onSuccess: (T) -> Unit = {}
    ) {
        scope.launch(contextProvider.io) {
            try {
                run().collect {
                    withContext(contextProvider.main) {
                        onSuccess(it)
                    }
                }
            } catch (e: Exception) {
                withContext(contextProvider.main) {
                    onError(e)
                }
            }
        }

    }

    fun cancel() = scope.coroutineContext.cancelChildren()
}