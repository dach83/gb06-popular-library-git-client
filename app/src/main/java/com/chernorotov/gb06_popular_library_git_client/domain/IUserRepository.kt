package com.chernorotov.gb06_popular_library_git_client.domain

import androidx.paging.PagingData
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getUsers(): Flow<PagingData<User>>

}