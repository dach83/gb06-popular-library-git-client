package com.chernorotov.gb06_popular_library_git_client.data.retrofit

import com.chernorotov.gb06_popular_library_git_client.data.retrofit.model.UserDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("user/{id}")
    fun getUser(
        @Path("id") userId: Int
    ): Single<UserDto>

    @GET("users")
    fun getUsers(
        @Query("since") sinceId: Int = 0,
        @Query("per_page") pageSize: Int = 30
    ): Single<List<UserDto>>

}