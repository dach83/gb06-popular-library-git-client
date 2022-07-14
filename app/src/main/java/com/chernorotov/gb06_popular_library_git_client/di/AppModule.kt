package com.chernorotov.gb06_popular_library_git_client.di

import android.content.Context
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.ui.userDetails.UserDetailsViewModel
import com.chernorotov.gb06_popular_library_git_client.ui.users.UsersViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideUsersViewModel(userRepository: IUserRepository) =
        UsersViewModel(userRepository)

    @Provides
    fun provideUserDetailsViewModel(userRepository: IUserRepository) =
        UserDetailsViewModel(userRepository)

}