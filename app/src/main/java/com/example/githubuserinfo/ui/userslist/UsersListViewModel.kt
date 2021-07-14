package com.example.githubuserinfo.ui.userslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserinfo.DisposeBag
import com.example.githubuserinfo.data.AccessToken
import com.example.githubuserinfo.data.User
import com.example.githubuserinfo.di.activity.ActivityScope
import com.example.githubuserinfo.network.GitHubApiClient
import com.example.githubuserinfo.network.GitHubAuthApiClient
import com.example.githubuserinfo.ui.common.AccessTokenController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class UsersListViewModel @Inject constructor(
    private val gitHubApiClient: GitHubApiClient,
    private val gitHubAuthApiClient: GitHubAuthApiClient,
    private val accessTokenController: AccessTokenController
): ViewModel(){

    private val _isShowProgress = MutableLiveData<Boolean>(false)
    val isShowProgress: LiveData<Boolean> get() = _isShowProgress

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>> get() = _usersList

    private val _accessToken = MutableLiveData<AccessToken>()
    val accessToken: LiveData<AccessToken> get() = _accessToken

    private val disposeBag = DisposeBag()

    fun fetchUserList() {
        _isShowProgress.postValue(true)
        disposeBag.add(gitHubApiClient.fetchUserList((_usersList.value?.last()?.id ?: -1L) + 1L, PER_PAGE, accessTokenController.token?.access_token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess{ response ->
                if (!response.isSuccessful || response.body() == null) {
                    Log.e(TAG, "fetchUserLists error: response is not successful or response.body is null")
                    return@doOnSuccess
                }
                Log.d("oauthTest", "userList success: " + response.body())
                _usersList.postValue(response.body())
            }
            .doOnError{
                Log.e(TAG, "fetchUserLists error: response is not successful or response.body is null")
            }
            .subscribe()
        )
        _isShowProgress.postValue(false)
    }

    fun fetchAccessToken(code: String) {
        disposeBag.add(gitHubAuthApiClient.fetchAccessToken(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { response ->
                Log.d("OAuthTest", "response: $response")
                if (!response.isSuccessful || response.body() == null) {
                    Log.e(TAG, "fetchAccessToken error: response is not successful or response.body is null")
                    return@doOnSuccess
                }
                Log.d("oauthTest", "responseBody: ${response.body()}")
                _accessToken.value = response.body()
            }
            .doOnError {
                Log.e(TAG, "fetchAccessToken error: response is not successful or response.body is null")
            }
            .subscribe()
        )
    }

    companion object {
        private val TAG = UsersListViewModel::class.java.simpleName
        private const val PER_PAGE = 20
    }
}
