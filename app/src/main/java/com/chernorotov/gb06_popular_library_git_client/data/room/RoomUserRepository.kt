package com.chernorotov.gb06_popular_library_git_client.data.room

import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import io.reactivex.rxjava3.core.Single

class RoomUserRepository(private val userDao: UserDao): IUserRepository {
    override fun getUser(userId: Int): Single<User> {
        TODO("Not yet implemented")
    }

    override fun getUsers(): Single<List<User>> {
        TODO("Not yet implemented")
    }
}