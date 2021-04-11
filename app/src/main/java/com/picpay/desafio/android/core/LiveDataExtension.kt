package com.picpay.desafio.android.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> viewState() = lazy {
    MutableLiveData<ViewState<T>>()
}

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data: T) {
    this.value = Success(data)
}

fun <T> MutableLiveData<ViewState<T>>.postError(throwable: Throwable? = null) {
    this.value = Error(throwable)
}

fun <T> MutableLiveData<ViewState<T>>.postLoading() {
    this.value = Loading()
}

fun <T> LiveData<ViewState<T>>.onPostValue(
    lifecycleOwner: LifecycleOwner,
    onLoading: (() -> Unit)? = null,
    onSuccess: ((T) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null
) {
    this.observe(lifecycleOwner, { viewState ->

        when (viewState) {

            is Loading -> {
                onLoading?.invoke()
            }

            is Success -> {
                viewState.data?.let {
                    onSuccess?.invoke(it)
                }
            }

            is Error -> {
                viewState.throwable?.let {
                    onError?.invoke(it)
                }
            }

            else -> Unit
        }
    })
}



