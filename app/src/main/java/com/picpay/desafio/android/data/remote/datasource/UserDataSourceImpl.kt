package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.data.remote.mapper.UserResponseMapper
import com.picpay.desafio.android.data.remote.service.PicPayService
import com.picpay.desafio.android.data.repository.datasource.UserDataSource
import kotlinx.coroutines.flow.flow

class UserDataSourceImpl(private val service: PicPayService): UserDataSource {

    override fun getUsers() = flow {
        emit(
            UserResponseMapper.toDomain(service.getUsers())
        )
    }
}