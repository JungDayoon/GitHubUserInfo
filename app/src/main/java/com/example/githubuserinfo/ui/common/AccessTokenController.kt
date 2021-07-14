package com.example.githubuserinfo.ui.common

import com.example.githubuserinfo.data.AccessToken
import com.example.githubuserinfo.di.app.AppScope
import javax.inject.Inject

@AppScope
class AccessTokenController @Inject constructor(){
    var token: AccessToken? = null
}
