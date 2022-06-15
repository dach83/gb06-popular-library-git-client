package com.chernorotov.gb06_popular_library_git_client.ui.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.chernorotov.gb06_popular_library_git_client.R
import com.chernorotov.gb06_popular_library_git_client.databinding.FragmentUsersBinding

class UsersFragment : Fragment(R.layout.fragment_users) {

    private val binding: FragmentUsersBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUserRecyclerView()
    }

    private fun setupUserRecyclerView() {
        binding.usersRecyclerView.adapter = UserPagingAdapter()
    }

}