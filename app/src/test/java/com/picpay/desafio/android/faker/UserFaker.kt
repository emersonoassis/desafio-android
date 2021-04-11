package com.picpay.desafio.android.faker

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.randomInt
import com.picpay.desafio.android.randomString

object UserFaker {
    fun getList() = listOf(getInstance())

    private fun getInstance() = User(
        id = randomInt(),
        img = randomString(),
        name = randomString(),
        username = randomString()
    )
}