package com.example.githubuserinfo.di.presentation

import com.example.githubuserinfo.ui.userslist.UsersListFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent
interface PresentationComponent {
    fun inject(usersListFragment: UsersListFragment)
}
