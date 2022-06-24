package com.chernorotov.gb06_popular_library_git_client.data.retrofit

import com.chernorotov.gb06_popular_library_git_client.data.retrofit.mappers.UserDtoMapper
import com.chernorotov.gb06_popular_library_git_client.data.room.mappers.UserEntityMapper
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import io.reactivex.rxjava3.core.Single

class RetrofitUserRepository(
    private val apiService: GithubApiService,
    private val userDtoMapper: UserDtoMapper = UserDtoMapper()
) : IUserRepository {

    override fun getUser(userId: Int): Single<User> =
        apiService.getUser(userId).map {
            userDtoMapper.mapToDomain(it)
        }


    override fun getUsers(): Single<List<User>> = apiService.getUsers().map { usersDto ->
        usersDto.map {
            userDtoMapper.mapToDomain(it)
        }
    }
}