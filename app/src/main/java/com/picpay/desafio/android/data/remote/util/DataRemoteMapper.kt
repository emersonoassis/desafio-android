package com.picpay.desafio.android.data.remote.util

abstract class DataRemoteMapper<in Remote, out Domain> {
    abstract fun toDomain(data: Remote): Domain
}