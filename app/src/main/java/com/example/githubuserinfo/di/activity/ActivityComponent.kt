package com.example.githubuserinfo.di.activity

import com.example.githubuserinfo.di.presentation.PresentationComponent
import com.example.githubuserinfo.di.viewmodel.ViewModelFactoryModule
import com.example.githubuserinfo.di.viewmodel.ViewModelModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class, ViewModelFactoryModule::class, ViewModelModule::class])
interface ActivityComponent {

    fun newPresentationComponent(): PresentationComponent

}
