package com.chernorotov.gb06_popular_library_git_client.domain

import com.chernorotov.gb06_popular_library_git_client.domain.model.User

interface IUserRepository {

    fun getUser(
        userId: Int,
        onSuccess: (User) -> Unit,
        onError: (error: Throwable) -> Unit
    )

    fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (error: Throwable) -> Unit
    )

}