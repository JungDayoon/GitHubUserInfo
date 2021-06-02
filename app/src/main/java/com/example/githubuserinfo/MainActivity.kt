package com.example.githubuserinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubuserinfo.ui.userslist.UsersListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, UsersListFragment())
            .commit()
    }
}
