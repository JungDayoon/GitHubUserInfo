package com.example.githubuserinfo.di.app

import android.app.Application
import com.example.githubuserinfo.Constants
import com.example.githubuserinfo.network.GitHubApi
import com.example.githubuserinfo.network.GitHubAuthApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class AppModule(private val application: Application) {

    val gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun application() = application

    @AppScope
    @Provides
    @Named(Constants.gitHub)
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.githubApiUrl)
            .build()

    @AppScope
    @Provides
    @Named(Constants.oAuth)
    fun oAuthRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.githubUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @AppScope
    @Provides
    fun githubApi(@Named(Constants.gitHub) retrofit: Retrofit): GitHubApi =
        retrofit.create(GitHubApi::class.java)

    @AppScope
    @Provides
    fun githubAuthApi(@Named(Constants.oAuth) retrofit: Retrofit): GitHubAuthApi =
        retrofit.create(GitHubAuthApi::class.java)
}
