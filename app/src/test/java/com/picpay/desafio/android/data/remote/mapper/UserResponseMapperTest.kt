package com.picpay.desafio.android.data.remote.mapper

import com.picpay.desafio.android.faker.UserResponseFaker
import com.picpay.desafio.android.domain.model.User
import org.junit.Assert
import org.junit.Test

class UserResponseMapperTest {

    @Test
    fun `object UserResponseMapper MUST return a list of Users`() {
        val fakerResponse = UserResponseFaker.getList()

        val expectedResult = fakerResponse.map { userResponse ->
            User(
                id = userResponse.id,
                img = userResponse.img,
                name = userResponse.name,
                username = userResponse.username
            )
        }

        val result = UserResponseMapper.toDomain(fakerResponse)

        Assert.assertEquals(expectedResult, result)
    }
}