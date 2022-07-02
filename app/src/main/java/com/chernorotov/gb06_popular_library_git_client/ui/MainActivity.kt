package com.chernorotov.gb06_popular_library_git_client.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chernorotov.gb06_popular_library_git_client.R
import com.chernorotov.gb06_popular_library_git_client.ui.userDetails.UserDetailsFragment
import com.chernorotov.gb06_popular_library_git_client.ui.users.UsersFragment

class MainActivity : AppCompatActivity(), INavController {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToUserDetails(userId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, UserDetailsFragment.getInstance(userId))
            .addToBackStack(null)
            .commit()
    }

}