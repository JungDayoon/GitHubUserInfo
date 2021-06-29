package com.example.githubuserinfo

object Constants {
    const val usersAccept = "Accept: application/vnd.github.v3+json"
    const val accessTokenAccept = "Accept: application/json"

    const val githubUrl = "https://github.com"
    const val githubApiUrl = "https://api.github.com"
    const val oAuthUrl = "login/oauth/authorize"

    const val clientIdKey = "client_id"
    const val redirectKey = "redirect_uri"
    const val queryParamCode = "code"
    const val accessTokenUrl = "login/oauth/access_token"

    const val gitHub = "github"
    const val oAuth = "oauth"

    fun redirectUri(scheme: String) = "$scheme://login"
}
