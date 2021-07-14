package com.example.githubuserinfo.network

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuserinfo.data.AccessToken
import com.example.githubuserinfo.data.User
import io.reactivex.Single
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import retrofit2.Response
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class ApiClientTest {
    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var gitHubAuthApiClient: GitHubAuthApiClient
    @Mock
    lateinit var gitHubApiClient: GitHubApiClient

    private var fakeUsersList = listOf<User>()
    private var fakeAccessToken = AccessToken("", "", "")

    private var mockedLog: MockedStatic<Log>? = null

    @Before
    fun setUp() {
        mockedLog = Mockito.mockStatic(Log::class.java)

        Mockito.`when`(gitHubApiClient.fetchUserList(0, 20, null)).thenReturn(Single.just(Response.success(fakeUsersList)))
        Mockito.`when`(gitHubAuthApiClient.fetchAccessToken("")).thenReturn(Single.just(Response.success(fakeAccessToken)))
    }

    @After
    fun tearDown() {
        mockedLog?.close()
    }

    @Test
    fun fetch_users_list_test() {
        gitHubApiClient.fetchUserList(0, 20, null)
            .test()
            .awaitDone(3000, TimeUnit.MILLISECONDS).assertOf{
                it.assertSubscribed()
                it.assertComplete()
                Assert.assertEquals(fakeUsersList.size, it.values()[0].body()?.size)
            }
    }

    @Test
    fun fetch_access_token_test() {
        gitHubAuthApiClient.fetchAccessToken("")
            .test()
            .awaitDone(3000, TimeUnit.MILLISECONDS).assertOf{
                it.assertSubscribed()
                it.assertComplete()
                Assert.assertEquals(fakeAccessToken, it.values()[0].body())
            }
    }
}
