package com.chernorotov.gb06_popular_library_git_client.ui.users

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.chernorotov.gb06_popular_library_git_client.R
import com.chernorotov.gb06_popular_library_git_client.app
import com.chernorotov.gb06_popular_library_git_client.databinding.FragmentUsersBinding
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import kotlinx.coroutines.flow.collectLatest

class UsersFragment : Fragment(R.layout.fragment_users) {

    private val binding: FragmentUsersBinding by viewBinding()
    private var adapter = UsersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStartScreenState()
        setupUserSwipeRefresh()
        setupUserRecyclerView()
        setupRefreshButton()
        refreshScreen()
    }

    private fun setupStartScreenState() {
        binding.usersRecyclerView.isVisible = false
        binding.errorScreen.errorLayout.isVisible = false
        binding.usersSwipeRefresh.isRefreshing = false
    }

    private fun setupRefreshButton() =
        binding.errorScreen.refreshButton.setOnClickListener {
            refreshScreen()
        }

    private fun setupUserSwipeRefresh() =
        binding.usersSwipeRefresh.setOnRefreshListener {
            refreshScreen()
        }

    private fun setupUserRecyclerView() {
        binding.usersRecyclerView.adapter = adapter
    }

    private fun showUsers(users: List<User>) {
        adapter.submitList(users)
        binding.usersSwipeRefresh.isRefreshing = false
        binding.errorScreen.errorLayout.isVisible = false
        binding.usersRecyclerView.isVisible = true
    }

    private fun showError(error: Throwable) {
        binding.usersSwipeRefresh.isRefreshing = false
        binding.errorScreen.errorLayout.isVisible = true
    }

    private fun showLoading() {
        binding.usersSwipeRefresh.isRefreshing = true
    }

    private fun refreshScreen() {
        showLoading()
        app.userRepository.getUsers(::showUsers, ::showError)
    }

    companion object {
        const val TAG = "@@@"
    }
}