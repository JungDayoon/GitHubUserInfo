package com.example.githubuserinfo.ui.userslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserinfo.R
import com.example.githubuserinfo.di.viewmodel.ViewModelFactory
import com.example.githubuserinfo.network.GitHubApi
import com.example.githubuserinfo.ui.common.BaseFragment
import kotlinx.android.synthetic.main.layout_users_list.*
import javax.inject.Inject

class UsersListFragment : BaseFragment() {
    private val viewModel: UsersListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(UsersListViewModel::class.java)
    }

    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_users_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector.inject(this)
        viewModel.fetchUserList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initAdapter()
    }

    private fun initViewModel() {
        viewModel.usersList.observe(viewLifecycleOwner, Observer {
            adapter.bindData(it)
        })
    }

    private fun initAdapter() {
        adapter = UsersAdapter(requireContext())
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = adapter
    }

    companion object {
        private val TAG = UsersListFragment::class.java.simpleName
    }
}
