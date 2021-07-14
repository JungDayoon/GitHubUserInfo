package com.example.githubuserinfo.userslist

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.githubuserinfo.MyApplication
import com.example.githubuserinfo.R
import com.example.githubuserinfo.di.activity.ActivityModule
import com.example.githubuserinfo.di.app.AppComponent
import com.example.githubuserinfo.di.app.AppModule
import com.example.githubuserinfo.di.app.DaggerAppComponent
import com.example.githubuserinfo.di.viewmodel.ViewModelFactory
import com.example.githubuserinfo.ui.userslist.UsersListActivity
import com.example.githubuserinfo.ui.userslist.UsersListFragment
import com.example.githubuserinfo.ui.userslist.UsersListViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

@RunWith(AndroidJUnit4::class)
@LargeTest
class UsersListActivityTest {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<UsersListActivity>()

    lateinit var context: Context

    private val testName = "mojombo"

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun showUsersListActivityTest() {
        onView(withId(R.id.login_menu)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun usersItemClickTest() {
        var resultString: String = ""
        activityScenarioRule.scenario.onActivity {
            resultString = it.getString(R.string.dialog_info, testName)
        }
        Thread.sleep(500)
        onView(withText(testName)).perform(click())
        onView(withText(resultString)).check(matches(isDisplayed()))
    }
}
