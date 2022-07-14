package com.chernorotov.gb06_popular_library_git_client.di

import android.content.Context
import androidx.room.Room
import com.chernorotov.gb06_popular_library_git_client.data.CachedUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.retrofit.GithubApiService
import com.chernorotov.gb06_popular_library_git_client.data.retrofit.RetrofitUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.room.RoomUserRepository
import com.chernorotov.gb06_popular_library_git_client.data.room.UserDao
import com.chernorotov.gb06_popular_library_git_client.data.room.UserDatabase
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    private val baseUrl = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGithubApiService(retrofit: Retrofit): GithubApiService =
        retrofit.create(GithubApiService::class.java)

    @Named("remote")
    @Provides
    @Singleton
    fun provideRemoteUserRepository(apiService: GithubApiService): IUserRepository =
        RetrofitUserRepository(apiService)

    @Provides
    @Singleton
    fun provideUserDatabase(context: Context): UserDatabase =
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "users.db"
        ).build()


    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao =
        userDatabase.userDao()

    @Named("local")
    @Provides
    @Singleton
    fun provideLocalUserRepository(userDao: UserDao): IUserRepository =
        RoomUserRepository(userDao)

    @Provides
    @Singleton
    fun provideUserRepository(
        @Named("remote") remoteUserRepository: IUserRepository,
        @Named("local") localUserRepository: IUserRepository
    ): IUserRepository = CachedUserRepository(remoteUserRepository, localUserRepository)

}