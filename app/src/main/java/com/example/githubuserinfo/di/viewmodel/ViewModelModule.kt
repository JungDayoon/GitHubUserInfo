package com.example.githubuserinfo.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.githubuserinfo.Constants
import com.example.githubuserinfo.network.GitHubApi
import com.example.githubuserinfo.network.GitHubApiClient
import com.example.githubuserinfo.network.GitHubAuthApiClient
import com.example.githubuserinfo.ui.common.AccessTokenController
import com.example.githubuserinfo.ui.userslist.UsersListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
class ViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    fun provideMainViewModel(
        gitHubApiClient: GitHubApiClient,
        gitHubAuthApiClient: GitHubAuthApiClient,
        accessTokenController: AccessTokenController
    ): ViewModel {
        return UsersListViewModel(gitHubApiClient, gitHubAuthApiClient, accessTokenController)
    }
}
