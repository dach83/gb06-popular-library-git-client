package com.chernorotov.gb06_popular_library_git_client.ui.userDetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.chernorotov.gb06_popular_library_git_client.R
import com.chernorotov.gb06_popular_library_git_client.databinding.FragmentUserDetailsBinding
import com.chernorotov.gb06_popular_library_git_client.domain.model.User
import com.chernorotov.gb06_popular_library_git_client.ui.ViewState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private val binding: FragmentUserDetailsBinding by viewBinding()
    private val viewModel: UserDetailsViewModel by viewModel()

    private val disposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()
        if (savedInstanceState == null) {
            requestUserDetails()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }

    private fun observeViewState() {
        disposable.add(viewModel.viewState.subscribe { renderViewState(it) })
    }

    private fun requestUserDetails() {
        val userId = requireArguments().getInt(KEY_USER_ID)
        viewModel.requestUserDetails(userId)
    }

    private fun renderViewState(viewState: ViewState<User>) {
        if (viewState is ViewState.Success) {
            binding.avatarImageView.load(viewState.data.avatarUrl)
            binding.nameTextView.text = viewState.data.login
        }
    }

    companion object {
        private const val KEY_USER_ID = "user_id"

        fun getInstance(userId: Int) = UserDetailsFragment().apply {
            arguments = bundleOf(KEY_USER_ID to userId)
        }
    }

}