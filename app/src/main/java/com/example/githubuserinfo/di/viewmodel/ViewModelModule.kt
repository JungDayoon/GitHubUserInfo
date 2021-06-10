package com.example.githubuserinfo.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.githubuserinfo.network.GitHubApi
import com.example.githubuserinfo.ui.userslist.UsersListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    fun provideMainViewModel(gitHubApi: GitHubApi): ViewModel {
        return UsersListViewModel(gitHubApi)
    }
}
