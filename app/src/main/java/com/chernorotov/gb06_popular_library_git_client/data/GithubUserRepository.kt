package com.chernorotov.gb06_popular_library_git_client.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chernorotov.gb06_popular_library_git_client.data.api.GithubApiService
import com.chernorotov.gb06_popular_library_git_client.data.api.mappers.UserDtoMapper
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import kotlinx.coroutines.flow.Flow

class GithubUserRepository(
    private val githubApiService: GithubApiService,
    private val userDtoMapper: UserDtoMapper = UserDtoMapper()
) : IUserRepository {

    private suspend fun userLoader(sinceId: Int, pageSize: Int): List<User> = githubApiService
        .getUsers(sinceId, pageSize)
        .map { userDtoMapper.mapToDomain(it) }

    override fun getUsers(): Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 90, enablePlaceholders = true)
    ) {
        UserPagingSource(::userLoader)
    }.flow
}