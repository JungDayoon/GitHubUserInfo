package com.example.githubuserinfo.userslist

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuserinfo.data.AccessToken
import com.example.githubuserinfo.data.User
import com.example.githubuserinfo.getOrAwaitValue
import com.example.githubuserinfo.network.GitHubApiClient
import com.example.githubuserinfo.network.GitHubAuthApiClient
import com.example.githubuserinfo.ui.userslist.UsersListViewModel
import io.reactivex.Single
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mockStatic
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class UsersListViewModelTest {
    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks lateinit var viewModel: UsersListViewModel
    @Mock lateinit var gitHubAuthApiClient: GitHubAuthApiClient
    @Mock lateinit var gitHubApiClient: GitHubApiClient

    private var fakeUsersList = listOf<User>()
    private var fakeAccessToken = AccessToken("", "", "")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockStatic(Log::class.java)

        `when`(gitHubApiClient.fetchUserList(0, 20, null)).thenReturn(Single.just(Response.success(fakeUsersList)))
        `when`(gitHubAuthApiClient.fetchAccessToken("")).thenReturn(Single.just(Response.success(fakeAccessToken)))
    }

    @After
    fun tearDown() {

    }

    @Test
    fun fetch_users_list_test() {
        viewModel.fetchUserList(null)
        viewModel.usersList.getOrAwaitValue()
        Assert.assertEquals(viewModel.usersList.value, fakeUsersList)
    }

    @Test
    fun fetch_access_token_test() {
        viewModel.fetchAccessToken("")
        viewModel.accessToken.getOrAwaitValue()
        Assert.assertEquals(viewModel.accessToken.value, fakeAccessToken)
    }
}
