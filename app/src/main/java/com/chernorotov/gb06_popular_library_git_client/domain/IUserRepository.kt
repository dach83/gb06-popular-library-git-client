package com.chernorotov.gb06_popular_library_git_client.domain

import com.chernorotov.gb06_popular_library_git_client.domain.model.User

interface IUserRepository {

    fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (error: Throwable) -> Unit
    )

}