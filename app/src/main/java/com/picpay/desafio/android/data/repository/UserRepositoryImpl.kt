package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.repository.datasource.UserDataSource
import com.picpay.desafio.android.domain.repository.UserRepository

class UserRepositoryImpl(private val userDataSource: UserDataSource) : UserRepository {

    override fun getUsers() = userDataSource.getUsers()

}