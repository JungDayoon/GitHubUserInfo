package com.example.githubuserinfo.ui.userslist

import android.os.Bundle
import com.example.githubuserinfo.R
import com.example.githubuserinfo.ui.common.BaseActivity

class UsersListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, UsersListFragment())
            .commit()
    }
}
