package com.chernorotov.gb06_popular_library_git_client.ui.users

import com.chernorotov.gb06_popular_library_git_client.domain.model.User

interface UsersContract {

    interface View {
        fun showLoading()
        fun showUsers(users: List<User>)
        fun showError(error: Throwable)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh()
    }

}