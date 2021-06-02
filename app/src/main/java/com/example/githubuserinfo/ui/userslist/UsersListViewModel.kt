package com.example.githubuserinfo.ui.userslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserinfo.data.User
import com.example.githubuserinfo.network.GitHubApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersListViewModel: ViewModel(){
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _isShowProgress = MutableLiveData<Boolean>(false)
    val isShowProgress: LiveData<Boolean> get() = _isShowProgress

    private var gitHubApi: GitHubApi

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: MutableLiveData<List<User>> get() = _usersList

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        gitHubApi = retrofit.create(GitHubApi::class.java)
    }

    fun fetchUserList() {
        Log.d(TAG, "fetchUsersList")
        coroutineScope.launch {
            _isShowProgress.value = true

            try {
                val response = gitHubApi.getUserList()
                if (response.isSuccessful && response.body() != null) {
                    _usersList.postValue(response.body()!!)
                } else {
                    Log.e(TAG, "fetchUserLists error: response is not successful or response.body is null")
                }
            } catch (t: Throwable) {
                Log.e(TAG, "fetchUserLists error: ${t.message}")
            } finally {
                _isShowProgress.value = false
            }
        }
    }

    companion object {
        private val TAG = UsersListViewModel::class.java.simpleName
    }
}
