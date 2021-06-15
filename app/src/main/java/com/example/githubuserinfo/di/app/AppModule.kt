package com.example.githubuserinfo.di.app

import android.app.Application
import com.example.githubuserinfo.Constants
import com.example.githubuserinfo.network.GitHubApi
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val application: Application) {

    val gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun application() = application

    @AppScope
    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .build()

    @AppScope
    @Provides
    fun githubApi(retrofit: Retrofit): GitHubApi =
        retrofit.create(GitHubApi::class.java)
}
