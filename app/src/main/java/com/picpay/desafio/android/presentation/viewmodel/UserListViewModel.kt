package com.picpay.desafio.android.presentation.viewmodel

import androidx.lifecycle.*
import com.picpay.desafio.android.core.*
import com.picpay.desafio.android.domain.interactor.GetUsers
import com.picpay.desafio.android.presentation.mapper.UserBindingMapper
import com.picpay.desafio.android.presentation.model.UserBinding
import org.koin.core.KoinComponent

class UserListViewModel : ViewModel(), KoinComponent, LifecycleObserver {

    private val getUsers: GetUsers by useCase()

    private val _userListViewState: MutableLiveData<ViewState<List<UserBinding>>> by viewState()
    val userListViewState: LiveData<ViewState<List<UserBinding>>> = _userListViewState

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchUserList() {
        if(_userListViewState.value !is Success) {
            _userListViewState.postLoading()
            getUsers(
                onSuccess = { users ->
                    _userListViewState.postSuccess(UserBindingMapper.toPresentationBinding(users))
                },
                onError = { throwable ->
                    _userListViewState.postError(throwable)
                }
            )
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun dispose() {
        getUsers.cancel()
    }
}