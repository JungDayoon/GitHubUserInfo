package com.example.githubuserinfo.ui.userslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserinfo.DisposeBag
import com.example.githubuserinfo.data.User
import com.example.githubuserinfo.di.activity.ActivityScope
import com.example.githubuserinfo.network.GitHubApi
import com.example.githubuserinfo.network.GitHubApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@ActivityScope
class UsersListViewModel @Inject constructor(val gitHubApiClient: GitHubApiClient): ViewModel(){

    private val _isShowProgress = MutableLiveData<Boolean>(false)
    val isShowProgress: LiveData<Boolean> get() = _isShowProgress

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: MutableLiveData<List<User>> get() = _usersList

    private val disposeBag = DisposeBag()

    fun fetchUserList() {
        disposeBag.add(gitHubApiClient.fetchUserList(0, 20)
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

    companion object {
        private val TAG = UsersListViewModel::class.java.simpleName
    }
}
