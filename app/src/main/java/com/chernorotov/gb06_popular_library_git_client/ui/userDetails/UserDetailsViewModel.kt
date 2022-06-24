package com.chernorotov.gb06_popular_library_git_client.ui.userDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import com.chernorotov.gb06_popular_library_git_client.ui.ViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject

class UserDetailsViewModel(private val repository: IUserRepository) : ViewModel() {

    private val _viewState = BehaviorSubject.create<ViewState<User>>()
    val viewState: Observable<ViewState<User>> get() = _viewState

    fun requestUserDetails(userId: Int) {
        _viewState.onNext(ViewState.Loading)
        repository.getUser(userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _viewState.onNext(ViewState.Success(it)) },
                onError = { _viewState.onNext(ViewState.Error(it)) }
            )
    }
}