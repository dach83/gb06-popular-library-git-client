package com.chernorotov.gb06_popular_library_git_client

import android.app.Application
import androidx.fragment.app.Fragment
import com.chernorotov.gb06_popular_library_git_client.di.AppComponent
import com.chernorotov.gb06_popular_library_git_client.di.AppModule
import com.chernorotov.gb06_popular_library_git_client.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()


    }
}


val Fragment.appComponent get() = (requireContext().applicationContext as App).appComponent