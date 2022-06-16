package com.chernorotov.gb06_popular_library_git_client

import android.app.Application
import androidx.fragment.app.Fragment
import com.chernorotov.gb06_popular_library_git_client.data.FakeUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.api.GithubApiService
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.ui.users.UsersPresenter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    // TODO(use di to create rest service)
    private val githubApiService = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubApiService::class.java)

    // TODO(use di to create a repository)
    private val userRepository: IUserRepository = //GithubUserRepository(githubApiService)
                                                  FakeUserRepository()

    // TODO(use di to create a presenter)
    val usersPresenter = UsersPresenter(userRepository)

}

val Fragment.app: App get() = requireContext().applicationContext as App