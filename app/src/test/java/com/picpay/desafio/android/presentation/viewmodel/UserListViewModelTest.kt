package com.picpay.desafio.android.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.picpay.desafio.android.core.Error
import com.picpay.desafio.android.core.Success
import com.picpay.desafio.android.core.ViewState
import com.picpay.desafio.android.domain.interactor.GetUsers
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.faker.UserFaker
import com.picpay.desafio.android.presentation.mapper.UserBindingMapper
import com.picpay.desafio.android.presentation.model.UserBinding
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mockito.times

class UserListViewModelTest : KoinTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getUsers = mock<GetUsers>()

    private val viewModel: UserListViewModel by lazy { UserListViewModel() }

    private var userListObserver: Observer<ViewState<List<UserBinding>>> = mock()


    private val testModule = module {
        single { getUsers }
    }

    @Before
    fun setup() {
        startKoin { modules(listOf(testModule)) }
        viewModel.userListViewState.observeForever(userListObserver)
    }

    @After
    fun finish() {
        stopKoin()
    }

    @Test
    fun `WHEN fetchUsers return onSuccess SHOULD change state to SUCESS with a list of UserBinding`() {
        val usersFaker = UserFaker.getList()
        val expectedResult = UserBindingMapper.toPresentationBinding(usersFaker)
        val captor = argumentCaptor<(List<User>) -> Unit>()
        whenever(
            getUsers(
                onError = any(),
                onSuccess = captor.capture()
            )
        ).doAnswer { invocationOnMock ->
            (invocationOnMock.getArgument<((List<User>) -> Unit)>(1)).invoke(
                usersFaker
            )
        }

        viewModel.fetchUserList()

        verify(userListObserver).onChanged(Success(expectedResult))
    }

    @Test
    fun `WHEN fetchUsers return onError SHOULD change state to ERROR with a throwable response`() {
        val expectedResult = Exception()
        val captor = argumentCaptor<(Throwable) -> Unit>()
        whenever(
            getUsers(
                onError = captor.capture(),
                onSuccess = any()
            )
        ).doAnswer { invocationOnMock ->
            (invocationOnMock.getArgument<(Throwable) -> Unit>(0)).invoke(
                expectedResult
            )
        }

        viewModel.fetchUserList()

        verify(userListObserver).onChanged(Error(expectedResult))
    }

    @Test
    fun `WHEN dispose SHOULD return unit`() {
        doNothing().`when`(getUsers).cancel()

        viewModel.dispose()

        verify(getUsers, times(1)).cancel()
    }
}