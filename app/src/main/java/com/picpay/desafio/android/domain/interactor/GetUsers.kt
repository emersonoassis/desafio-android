package com.picpay.desafio.android.domain.interactor

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetUsers(private val repository: UserRepository, scope: CoroutineScope) :
    UseCase<List<User>>(scope) {
    override fun run(): Flow<List<User>> = repository.getUsers()
}