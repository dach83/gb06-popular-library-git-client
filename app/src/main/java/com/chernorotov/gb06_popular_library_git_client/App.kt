package com.chernorotov.gb06_popular_library_git_client

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.chernorotov.gb06_popular_library_git_client.data.CachedUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.retrofit.GithubApiService
import com.chernorotov.gb06_popular_library_git_client.data.retrofit.RetrofitUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.room.RoomUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.room.UserDatabase
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    lateinit var userRepository: IUserRepository

    override fun onCreate() {
        super.onCreate()

        val userDb = Room.databaseBuilder(
            this,
            UserDatabase::class.java,
            "users.db"
        ).build()

        val gitApi = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(GithubApiService::class.java)

        val retrofitUserRepository = RetrofitUserRepository(gitApi)
        val roomUserRepository = RoomUserRepository(userDb.userDao())

        userRepository = CachedUserRepository(
            retrofitUserRepository,
            roomUserRepository
        )
    }

}

val Fragment.app: App get() = requireContext().applicationContext as App