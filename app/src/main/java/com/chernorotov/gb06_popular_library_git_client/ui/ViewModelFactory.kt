package com.chernorotov.gb06_popular_library_git_client.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import com.chernorotov.gb06_popular_library_git_client.ui.userDetails.UserDetailsViewModel
import com.chernorotov.gb06_popular_library_git_client.ui.users.UsersViewModel

class ViewModelFactory(private val userRepository: IUserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when (modelClass) {
            UsersViewModel::class.java -> UsersViewModel(userRepository) as T
            UserDetailsViewModel::class.java -> UserDetailsViewModel(userRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel")
        }
}