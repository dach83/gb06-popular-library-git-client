package com.chernorotov.gb06_popular_library_git_client.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import com.chernorotov.gb06_popular_library_git_client.ui.ViewState

class UsersViewModel(private val userRepository: IUserRepository) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<List<User>>>(ViewState.Loading)
    val viewState: LiveData<ViewState<List<User>>> get() = _viewState

    fun requestUsers() {
        _viewState.postValue(ViewState.Loading)
        userRepository.getUsers(
            onSuccess = { _viewState.postValue(ViewState.Success(it)) },
            onError = { _viewState.postValue(ViewState.Error(it)) }
        )
    }

}