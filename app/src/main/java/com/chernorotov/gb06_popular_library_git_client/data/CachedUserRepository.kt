package com.chernorotov.gb06_popular_library_git_client.data

import android.util.Log
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class CachedUserRepository(
    private val retrofitRepository: IUserRepository,
    private val roomRepository: IUserRepository
) : IUserRepository {

    override fun getUser(userId: Int): Single<User> {
        retrofitRepository.getUser(userId)
            .observeOn(Schedulers.io())
            .subscribeBy(onSuccess = { roomRepository.insert(listOf(it)) })

        return roomRepository.getUser(userId)
    }

    override fun getUsers(): Single<List<User>> {
        Log.d("@@@", "getUsers")
        retrofitRepository.getUsers()
            .observeOn(Schedulers.io())
            .subscribeBy(onSuccess = roomRepository::insert)

        return roomRepository.getUsers()
    }

}