package com.chernorotov.gb06_popular_library_git_client.ui.users

import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository

class UsersPresenter(private val userRepository: IUserRepository) : UsersContract.Presenter {

    private var view: UsersContract.View? = null

    override fun attach(view: UsersContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun refreshScreen() {
        view?.let { view ->
            view.showLoading()
            userRepository.getUsers(view::showUsers, view::showError)
        }
    }

}