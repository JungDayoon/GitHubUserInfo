package com.example.githubuserinfo.network

import com.example.githubuserinfo.data.User
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import javax.inject.Inject

interface GitHubApi {
    @GET("/users")
    fun getUserList(
        @Header("accept") accept: String,
        @Query("authorization") authorization: String?,
        @Query("since") since: Long?,
        @Query("per_page") perPage: Int?
    ): Single<Response<List<User>>>
}
