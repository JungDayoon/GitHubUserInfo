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
import kotlinx.android.synthetic.main.layout_users_list.*

class UsersListFragment : Fragment() {

//    private lateinit var usersListView: UsersListView

    private lateinit var viewModel: UsersListViewModel

    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_users_list, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(UsersListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UsersAdapter()

        recycler.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recycler.adapter = adapter

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
            adapter.bindData(it)
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchUserList()
    }

    companion object {
        private val TAG = UsersListFragment::class.java.simpleName
    }
}
