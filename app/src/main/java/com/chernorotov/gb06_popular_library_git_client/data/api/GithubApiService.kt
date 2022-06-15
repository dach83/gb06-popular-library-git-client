package com.chernorotov.gb06_popular_library_git_client.data.api

import com.chernorotov.gb06_popular_library_git_client.data.api.model.UserDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("since") sinceId: Int,
        @Query("per_page") pageSize: Int
    ): List<UserDto>

}