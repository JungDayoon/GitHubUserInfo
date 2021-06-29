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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class UsersListViewModel @Inject constructor(
    private val gitHubApiClient: GitHubApiClient,
    private val gitHubAuthApiClient: GitHubAuthApiClient
): ViewModel(){

    private val _isShowProgress = MutableLiveData<Boolean>(false)
    val isShowProgress: LiveData<Boolean> get() = _isShowProgress

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>> get() = _usersList

    private val _accessToken = MutableLiveData<AccessToken>()
    val accessToken: LiveData<AccessToken> get() = _accessToken

    private val disposeBag = DisposeBag()

    fun fetchUserList(token: String?) {
        disposeBag.add(gitHubApiClient.fetchUserList(0, 20, token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess{ response ->
                if (!response.isSuccessful || response.body() == null) {
                    Log.e(TAG, "fetchUserLists error: response is not successful or response.body is null")
                    return@doOnSuccess
                }
                _usersList.postValue(response.body())
            }
            .doOnError{
                Log.e(TAG, "fetchUserLists error: response is not successful or response.body is null")
            }
            .subscribe()
        )
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
    }
}
