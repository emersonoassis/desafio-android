package com.picpay.desafio.android.core

sealed class ViewState<Data>
class Loading<Data> : ViewState<Data>()
data class Error<Data> (val throwable: Throwable?) : ViewState<Data>()
data class Success<Data>(val data: Data) : ViewState<Data>()
