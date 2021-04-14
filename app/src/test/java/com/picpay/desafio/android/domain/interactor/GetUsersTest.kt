package com.picpay.desafio.android.domain.interactor

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.domain.TestContextProvider
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.util.ThreadContextProvider
import com.picpay.desafio.android.faker.UserFaker
import com.picpay.desafio.android.testFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class GetUsersTest : KoinTest {

    private val repository: UserRepository = mock()

    private val testModule = module {
        single<ThreadContextProvider> { TestContextProvider() }
    }

    private val useCase: GetUsers by lazy {
        GetUsers(
            repository,
            CoroutineScope(Dispatchers.Unconfined)
        )
    }

    @Before
    fun setup() {
        startKoin { listOf(testModule) }
    }

    @After
    fun finish() {
        stopKoin()
    }

    @Test
    fun `WHEN getUsers is success SHOULD return a list of users`() {
        val expectedValue = UserFaker.getList()
        whenever(repository.getUsers()).thenReturn(flowOf(expectedValue))
        useCase.run().testFlow {
            verify(repository, times(1)).getUsers()
            Assert.assertEquals(expectedValue, this)
        }


    }

    @Test(expected = Exception::class)
    fun `WHEN getUsers is failure SHOULD return a Exception`() {
        whenever(repository.getUsers()).thenThrow(Exception())
        useCase.run().testFlow{
            verify(repository, times(1)).getUsers()
        }

    }
}