package com.chernorotov.gb06_popular_library_git_client.di

import com.chernorotov.gb06_popular_library_git_client.ui.userDetails.UserDetailsViewModel
import com.chernorotov.gb06_popular_library_git_client.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<UsersViewModel> {
        UsersViewModel(get())
    }

    viewModel<UserDetailsViewModel> {
        UserDetailsViewModel(get())
    }

}