package com.example.githubuserinfo.di.app

import android.app.Application
import com.example.githubuserinfo.Constants
import com.example.githubuserinfo.network.GitHubApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val application: Application) {

    @Provides
    fun application() = application

    @AppScope
    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @AppScope
    @Provides
    fun githubApi(retrofit: Retrofit): GitHubApi =
        retrofit.create(GitHubApi::class.java)
}
