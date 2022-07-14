package com.chernorotov.gb06_popular_library_git_client.di

import com.chernorotov.gb06_popular_library_git_client.ui.userDetails.UserDetailsViewModel
import com.chernorotov.gb06_popular_library_git_client.ui.users.UsersViewModel
import ru.gidural.mykoin.get
import ru.gidural.mykoin.module
import ru.gidural.mykoin.viewModel

val appModule = module {

    viewModel<UsersViewModel> {
        UsersViewModel(get())
    }

    viewModel<UserDetailsViewModel> {
        UserDetailsViewModel(get())
    }

}