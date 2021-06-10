package com.example.githubuserinfo.ui.common

import androidx.appcompat.app.AppCompatActivity
import com.example.githubuserinfo.MyApplication
import com.example.githubuserinfo.di.activity.ActivityModule

open class BaseActivity: AppCompatActivity() {
    private val appComponent get() = (application as MyApplication).appComponent

    val activityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this))
    }

    val presentationComponent by lazy {
        activityComponent.newPresentationComponent()
    }

    protected val injector get() = presentationComponent
}
