package com.example.githubuserinfo.network

import com.example.githubuserinfo.Constants
import com.example.githubuserinfo.data.AccessToken
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface GitHubAuthApi {
    @FormUrlEncoded
    @POST(Constants.accessTokenUrl)
    fun getAccessToken(
        @Header("accept") accept: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): Single<Response<AccessToken>>
}
