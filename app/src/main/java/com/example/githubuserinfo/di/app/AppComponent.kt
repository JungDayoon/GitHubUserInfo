package com.example.githubuserinfo.di.app

import com.example.githubuserinfo.di.activity.ActivityComponent
import com.example.githubuserinfo.di.activity.ActivityModule
import com.example.githubuserinfo.network.GitHubApiClient
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

    fun inject(gitHubApiClient: GitHubApiClient)

}
