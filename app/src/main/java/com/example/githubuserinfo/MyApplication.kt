package com.example.githubuserinfo

import android.app.Application
import com.example.githubuserinfo.di.app.AppModule
import com.example.githubuserinfo.di.app.DaggerAppComponent

class MyApplication: Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}
