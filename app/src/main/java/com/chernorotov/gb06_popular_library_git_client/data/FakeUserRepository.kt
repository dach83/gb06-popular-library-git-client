package com.chernorotov.gb06_popular_library_git_client.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class FakeUserRepository : IUserRepository {

    private val fakeUsers = listOf(
        User(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
        User(2, "defunkt", "https://avatars.githubusercontent.com/u/2?v=4"),
        User(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4"),
        User(4, "wycats", "https://avatars.githubusercontent.com/u/4?v=4"),
        User(5, "ezmobius", "https://avatars.githubusercontent.com/u/5?v=4"),
        User(6, "ivey", "https://avatars.githubusercontent.com/u/6?v=4"),
        User(7, "evanphx", "https://avatars.githubusercontent.com/u/7?v=4"),
    )

    private suspend fun userLoader(sinceId: Int, pageSize: Int): List<User> {
        delay(3000)
        return fakeUsers
    }

    override fun getUsers(): Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = true)
    ) {
        UserPagingSource(::userLoader)
    }.flow

}