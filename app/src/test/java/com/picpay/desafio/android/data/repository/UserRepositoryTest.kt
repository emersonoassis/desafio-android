package com.picpay.desafio.android.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.repository.datasource.UserDataSource
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UserRepositoryTest {

    private val dataSource = mock<UserDataSource>()

    private val repository by lazy { UserRepositoryImpl(dataSource) }

    @Test
    fun `WHEN getUsers is success SHOULD return a list of users`() = runBlocking {
        val expectedResult = emptyList<User>()
        whenever(dataSource.getUsers()).thenReturn(flowOf(expectedResult))
        val result = repository.getUsers().first()

        Assert.assertEquals(expectedResult, result)
    }
}