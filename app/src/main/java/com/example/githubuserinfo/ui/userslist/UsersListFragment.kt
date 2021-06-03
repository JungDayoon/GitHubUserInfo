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
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserinfo.R
import com.example.githubuserinfo.data.User
import kotlinx.android.synthetic.main.layout_users_list.*

class UsersListFragment : Fragment() {

    private lateinit var viewModel: UsersListViewModel

    private val adapter: UsersAdapter by lazy {
        UsersAdapter(requireContext(), userList)
    }

    private var userList = listOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_users_list, container, false)
        initViewModel()
        initAdapter()
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(UsersListViewModel::class.java)
        viewModel.fetchUserList()
    }

    private fun initViewModel() {
        viewModel.isShowProgress.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "isShowProgress: $it")
            if (it) {
                swipeRefresh.visibility = View.VISIBLE
            } else {
                swipeRefresh.visibility = View.GONE
            }
        })

        viewModel.usersList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "usersList: $it")
            userList = it
            adapter.notifyDataSetChanged()
        })
    }

    private fun initAdapter() {

        recycler.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recycler.adapter = adapter

    }

    companion object {
        private val TAG = UsersListFragment::class.java.simpleName
    }
}
