package com.chernorotov.gb06_popular_library_git_client

import android.app.Application
import com.chernorotov.gb06_popular_library_git_client.di.appModule
import com.chernorotov.gb06_popular_library_git_client.di.dataModule
import com.chernorotov.gb06_popular_library_git_client.domain.IUserRepository
import ru.gidural.mykoin.startKoin

class App : Application() {

    lateinit var userRepository: IUserRepository

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule, dataModule)
        }

    }
}
