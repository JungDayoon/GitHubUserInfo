package com.example.githubuserinfo.network

import com.example.githubuserinfo.Constants
import com.example.githubuserinfo.OAuth
import com.example.githubuserinfo.data.AccessToken
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GitHubAuthApiClient @Inject constructor(
        private val gitHubAuthApi: GitHubAuthApi
) {
    fun fetchAccessToken(code: String): Single<Response<AccessToken>> {
        return gitHubAuthApi.getAccessToken(Constants.accessTokenAccept, OAuth.clientId, OAuth.clientSecret, code)
    }
}
