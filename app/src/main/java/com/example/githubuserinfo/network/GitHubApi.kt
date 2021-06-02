package com.example.githubuserinfo.network

import com.example.githubuserinfo.data.User
import retrofit2.Response
import retrofit2.http.GET

interface GitHubApi {
    @GET("users")
    suspend fun getUserList(): Response<List<User>>
}
