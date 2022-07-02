package com.chernorotov.gb06_popular_library_git_client.di

import androidx.room.Room
import com.chernorotov.gb06_popular_library_git_client.data.CachedUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.retrofit.GithubApiService
import com.chernorotov.gb06_popular_library_git_client.data.retrofit.RetrofitUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.room.RoomUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.room.UserDao
import com.chernorotov.gb06_popular_library_git_client.data.room.UserDatabase
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    val baseUrl = "https://api.github.com/"

    single<UserDatabase> {
        Room.databaseBuilder(
            get(),
            UserDatabase::class.java,
            "users.db"
        ).build()
    }

    single<UserDao> {
        get<UserDatabase>().userDao()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    single<GithubApiService> {
        get<Retrofit>().create(GithubApiService::class.java)
    }

    single<IUserRepository> {
        val retrofitUserRepository = RetrofitUserRepository(get())
        val roomUserRepository = RoomUserRepository(get())
        CachedUserRepository(retrofitUserRepository, roomUserRepository)
    }

}

