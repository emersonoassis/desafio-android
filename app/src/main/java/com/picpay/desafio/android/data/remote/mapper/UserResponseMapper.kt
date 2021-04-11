package com.picpay.desafio.android.data.remote.mapper

import com.picpay.desafio.android.data.remote.model.UserResponse
import com.picpay.desafio.android.data.remote.util.DataRemoteMapper
import com.picpay.desafio.android.domain.model.User

object UserResponseMapper : DataRemoteMapper<List<UserResponse>, List<User>>() {
    override fun toDomain(data: List<UserResponse>) = data.map { userResponse ->
        User(
            id = userResponse.id,
            img = userResponse.img,
            name = userResponse.name,
            username = userResponse.username
        )
    }
}