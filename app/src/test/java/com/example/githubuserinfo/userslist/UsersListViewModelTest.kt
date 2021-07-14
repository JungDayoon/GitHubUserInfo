package com.example.githubuserinfo.userslist

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuserinfo.RxSchedulerRule
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
import org.mockito.MockedStatic
import org.mockito.Mockito.*
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
    var testSchedulerRule = RxSchedulerRule()

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: UsersListViewModel
    @Mock lateinit var gitHubAuthApiClient: GitHubAuthApiClient
    @Mock lateinit var gitHubApiClient: GitHubApiClient

    private var fakeUsersList = listOf<User>()
    private var fakeAccessToken = AccessToken("", "", "")

    private var mockedLog: MockedStatic<Log>? = null

    @Before
    fun setUp() {
        mockedLog = mockStatic(Log::class.java)

        viewModel = spy(UsersListViewModel(gitHubApiClient, gitHubAuthApiClient))

        `when`(gitHubApiClient.fetchUserList(0, 20, null)).thenReturn(Single.just(Response.success(fakeUsersList)))
        `when`(gitHubAuthApiClient.fetchAccessToken("")).thenReturn(Single.just(Response.success(fakeAccessToken)))
    }

    @After
    fun tearDown() {
        mockedLog?.close()
    }

    @Test
    fun fetch_users_list_test() {
        viewModel.fetchUserList()
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
