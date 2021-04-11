package com.picpay.desafio.android.data.repository.datasource

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun getUsers(): Flow<List<User>>
}