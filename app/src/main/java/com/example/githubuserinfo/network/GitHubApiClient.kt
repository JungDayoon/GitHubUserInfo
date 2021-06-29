package com.example.githubuserinfo.network

import com.example.githubuserinfo.Constants
import com.example.githubuserinfo.data.User
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GitHubApiClient @Inject constructor(
        private val gitHubApi: GitHubApi
){
    fun fetchUserList(since: Long, perPage: Int, token: String?): Single<Response<List<User>>> {
        return gitHubApi.getUserList(Constants.usersAccept, token, since, perPage)
    }
}
