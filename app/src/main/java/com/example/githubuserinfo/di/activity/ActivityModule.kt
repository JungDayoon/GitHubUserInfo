package com.example.githubuserinfo.di.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule (val activity: AppCompatActivity) {

    @Provides
    fun activity() = activity

    @Provides
    fun layoutInflater(activity: AppCompatActivity) = LayoutInflater.from(activity)

    @Provides
    fun fragementManager(activity: AppCompatActivity) = activity.supportFragmentManager
}
