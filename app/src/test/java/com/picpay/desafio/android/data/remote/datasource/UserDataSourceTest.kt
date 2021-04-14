package com.picpay.desafio.android.data.remote.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.remote.mapper.UserResponseMapper
import com.picpay.desafio.android.data.remote.service.PicPayService
import com.picpay.desafio.android.faker.UserResponseFaker
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.times

class UserDataSourceTest {

    private val service = mock<PicPayService>()

    private val dataSource by lazy { UserDataSourceImpl(service) }

    @Test
    fun `WHEN getUsers returns SUCCESS SHOULD return a list of UserResponse`() = runBlocking {
        val dummyResponse = UserResponseFaker.getList()
        val expectedValue = UserResponseMapper.toDomain(dummyResponse)
        whenever(service.getUsers()).thenReturn(dummyResponse)

        val result = dataSource.getUsers().first()

        verify(service, times(1)).getUsers()
        Assert.assertEquals(expectedValue, result)
    }

    @Test(expected = Exception::class)
    fun `WHEN getUsers returns ERROR SHOULD return a exception`() {
        runBlocking {
            whenever(service.getUsers()).thenThrow(Exception())

            dataSource.getUsers().first()
        }
    }

}