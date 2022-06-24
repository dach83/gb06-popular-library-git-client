package com.chernorotov.gb06_popular_library_git_client.data

import com.chernorotov.gb06_popular_library_git_client.data.api.GithubApiService
import com.chernorotov.gb06_popular_library_git_client.data.api.mappers.UserDtoMapper
import com.chernorotov.gb06_popular_library_git_client.data.api.model.UserDto
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubUserRepository(
    private val apiService: GithubApiService,
    private val userDtoMapper: UserDtoMapper = UserDtoMapper()
) : IUserRepository {

    override fun getUser(
        userId: Int,
        onSuccess: (User) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        apiService.getUser(userId).enqueue(object : Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                if (response.isSuccessful) {
                    val userDto = response.body()
                    if (userDto != null) {
                        onSuccess(userDtoMapper.mapToDomain(userDto))
                        return
                    }
                }

                onError(IllegalStateException("User not found"))
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                onError(t)
            }
        })
    }

    override fun getUsers(onSuccess: (List<User>) -> Unit, onError: (error: Throwable) -> Unit) {
        apiService.getUsers().enqueue(object : Callback<List<UserDto>> {
            override fun onResponse(call: Call<List<UserDto>>, response: Response<List<UserDto>>) {
                if (response.isSuccessful) {
                    val users = response.body()?.map { userDtoMapper.mapToDomain(it) } ?: emptyList()
                    onSuccess(users)
                } else {
                    onError(IllegalStateException("Something went wrong"))
                }
            }

            override fun onFailure(call: Call<List<UserDto>>, t: Throwable) {
                onError(t)
            }
        })
    }

}