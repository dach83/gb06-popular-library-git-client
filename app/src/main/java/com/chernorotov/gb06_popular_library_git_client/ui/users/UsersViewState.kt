package com.chernorotov.gb06_popular_library_git_client.ui.users

import com.chernorotov.gb06_popular_library_git_client.domain.model.User


sealed class UsersViewState {
    object Loading: UsersViewState()
    data class Success(val users: List<User>): UsersViewState()
    data class Error(val error: Throwable): UsersViewState()
}