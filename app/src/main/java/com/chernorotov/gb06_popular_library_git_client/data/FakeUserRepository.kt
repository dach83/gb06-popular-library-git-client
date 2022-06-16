package com.chernorotov.gb06_popular_library_git_client.data

import android.os.Handler
import android.os.Looper
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import kotlin.random.Random

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

    override fun getUsers(onSuccess: (List<User>) -> Unit, onError: (error: Throwable) -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (Random.nextBoolean()) {
                    onSuccess(fakeUsers)
                } else {
                    onError(IllegalStateException("Something went wrong!"))
                }
            }, DATA_LOADING_DELAY
        )
    }

    companion object {
        const val DATA_LOADING_DELAY = 2_000L
    }

}