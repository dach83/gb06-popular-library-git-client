package com.chernorotov.gb06_popular_library_git_client.di

import com.chernorotov.gb06_popular_library_git_client.ui.userDetails.UserDetailsFragment
import com.chernorotov.gb06_popular_library_git_client.ui.users.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {

    fun injectUsersFragment(usersFragment: UsersFragment)

    fun injectUserDetailsFragment(userDetailsFragment: UserDetailsFragment)
}