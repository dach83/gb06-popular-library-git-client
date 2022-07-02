package com.chernorotov.gb06_popular_library_git_client

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.chernorotov.gb06_popular_library_git_client.data.CachedUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.FakeUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.retrofit.GithubApiService
import com.chernorotov.gb06_popular_library_git_client.data.retrofit.RetrofitUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.room.RoomUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.room.UserDatabase
import com.chernorotov.gb06_popular_library_git_client.di.appModule
import com.chernorotov.gb06_popular_library_git_client.di.dataModule
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    lateinit var userRepository: IUserRepository

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule, dataModule)
        }

    }
}
