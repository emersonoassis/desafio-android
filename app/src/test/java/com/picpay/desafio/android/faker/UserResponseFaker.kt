package com.picpay.desafio.android.faker

import com.picpay.desafio.android.data.remote.model.UserResponse
import com.picpay.desafio.android.randomInt
import com.picpay.desafio.android.randomString

object UserResponseFaker {
    fun getList() = listOf(getInstance())

    private fun getInstance() = UserResponse(
        id = randomInt(),
        img = randomString(),
        name = randomString(),
        username = randomString()
    )
}