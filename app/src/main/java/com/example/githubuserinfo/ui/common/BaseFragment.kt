package com.example.githubuserinfo.ui.common

import androidx.fragment.app.Fragment
import com.example.githubuserinfo.di.viewmodel.ViewModelFactory
import javax.inject.Inject

open class BaseFragment: Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity)
            .activityComponent
            .newPresentationComponent()
    }

    protected val injector get() = presentationComponent

}
