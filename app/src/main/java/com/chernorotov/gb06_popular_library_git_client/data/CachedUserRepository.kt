package com.chernorotov.gb06_popular_library_git_client.data

import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import io.reactivex.rxjava3.core.Single

class CachedUserRepository(
    private val retrofitRepository: IUserRepository,
    private val roomRepository: IUserRepository
): IUserRepository {

    override fun getUser(userId: Int): Single<User> {
        return retrofitRepository.getUser(userId)
    }

    override fun getUsers(): Single<List<User>> {
        return retrofitRepository.getUsers()
    }

}