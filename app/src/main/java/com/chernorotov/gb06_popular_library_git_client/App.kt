package com.chernorotov.gb06_popular_library_git_client

import android.app.Application
import androidx.fragment.app.Fragment
import com.chernorotov.gb06_popular_library_git_client.data.FakeUserRepository
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository

class App: Application() {

    val userRepository: IUserRepository = FakeUserRepository()

}

val Fragment.app: App get() = requireContext().applicationContext as App