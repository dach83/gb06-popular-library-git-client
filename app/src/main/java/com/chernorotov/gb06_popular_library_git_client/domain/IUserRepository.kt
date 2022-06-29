package com.chernorotov.gb06_popular_library_git_client.domain

import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface IUserRepository {
    fun getUser(userId: Int): Flowable<User>
    fun getUsers(): Flowable<List<User>>
    fun insert(users: List<User>) {}
}