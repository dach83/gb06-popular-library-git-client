package com.chernorotov.gb06_popular_library_git_client.ui.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.chernorotov.gb06_popular_library_git_client.R
import com.chernorotov.gb06_popular_library_git_client.app
import com.chernorotov.gb06_popular_library_git_client.databinding.FragmentUsersBinding
import kotlinx.coroutines.flow.collectLatest

class UsersFragment : Fragment(R.layout.fragment_users) {

    private val binding: FragmentUsersBinding by viewBinding()
    private var adapter = UserPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUserSwipeRefresh()
        setupUserRecyclerView()
    }

    private fun setupUserSwipeRefresh() {
        binding.usersSwipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun setupUserRecyclerView() {
        binding.usersRecyclerView.adapter = adapter
        observeUserFlow(adapter)
        observeLoadingState(adapter)
    }

    private fun observeUserFlow(adapter: UserPagingAdapter) {
        lifecycleScope.launchWhenStarted {
            app.userRepository.getUsers().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun observeLoadingState(adapter: UserPagingAdapter) = adapter.addLoadStateListener {
        binding.usersSwipeRefresh.isRefreshing = it.refresh == LoadState.Loading
    }

}