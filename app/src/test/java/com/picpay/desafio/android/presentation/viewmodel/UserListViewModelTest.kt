package com.picpay.desafio.android.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.picpay.desafio.android.core.Success
import com.picpay.desafio.android.domain.interactor.GetUsers
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.faker.UserFaker
import org.junit.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class UserListViewModelTest : KoinTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getUsers: GetUsers = mock()

    private val testModule = module {
        single { getUsers }
    }

    private val viewModel: UserListViewModel by lazy { UserListViewModel() }

    @Before
    fun setup() {
        startKoin { testModule }
    }

    @After
    fun finish() {
        stopKoin()
    }

    @Test
    fun fetchUsers() {
       /* val expectedValue =  UserFaker.getList()
        val callBack: (List<User>) -> Unit = mock()
        val captor = argumentCaptor<(List<User>) -> Unit>()

        whenever(getUsers.run()).then { invocation ->
            (invocation.arguments[1] as (List<User>) -> Unit).invoke(expectedValue)
        }


        viewModel.fetchUserList()

        verify(getUsers, times(1)).invoke(
            onError = any(),
            onSuccess = captor.capture()
        )*/
    }
}