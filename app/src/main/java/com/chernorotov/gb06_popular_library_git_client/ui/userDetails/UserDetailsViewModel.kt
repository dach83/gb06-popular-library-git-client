package com.chernorotov.gb06_popular_library_git_client.ui.userDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import com.chernorotov.gb06_popular_library_git_client.ui.ViewState

class UserDetailsViewModel(private val repository: IUserRepository) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<User>>()
    val viewState: LiveData<ViewState<User>> get() = _viewState

    fun requestUserDetails(userId: Int) {
        _viewState.postValue(ViewState.Loading)
        repository.getUser(
            userId = userId,
            onSuccess = { _viewState.postValue(ViewState.Success(it)) },
            onError = { _viewState.postValue(ViewState.Error(it)) }
        )
    }
}