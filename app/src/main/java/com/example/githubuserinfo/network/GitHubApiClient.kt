package com.example.githubuserinfo.network

import android.util.Log
import com.example.githubuserinfo.Constants
import com.example.githubuserinfo.MyApplication
import com.example.githubuserinfo.data.User
import com.example.githubuserinfo.di.app.AppScope
import com.example.githubuserinfo.ui.userslist.UsersListViewModel
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GitHubApiClient @Inject constructor(
    val gitHubApi: GitHubApi
){
    fun fetchUserList(since: Long, perPage: Int): Single<Response<List<User>>> {
        return gitHubApi.getUserList(Constants.USER_ACCEPT, since, perPage)
    }
}
