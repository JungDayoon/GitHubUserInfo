package com.example.githubuserinfo.ui.userslist

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserinfo.Constants
import com.example.githubuserinfo.OAuth
import com.example.githubuserinfo.R
import com.example.githubuserinfo.ui.common.BaseFragment
import kotlinx.android.synthetic.main.layout_users_list.*
import javax.inject.Inject

class UsersListFragment : BaseFragment() {
    private val viewModel: UsersListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(UsersListViewModel::class.java)
    }

    @Inject lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_users_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchUserList(null)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initAdapter()
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()

        val uri = activity?.intent?.data

        Log.d(TAG, "uri: $uri")
        uri?.let {
            if (uri.toString().startsWith(Constants.redirectUri(getString(R.string.scheme_name)))) {
                uri.getQueryParameter(Constants.queryParamCode)?.let { code ->
                    viewModel.fetchAccessToken(code)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.login_menu -> {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        String.format(
                            "%s/%s?%s=%s&%s=%s",
                            Constants.githubUrl,
                            Constants.oAuthUrl,
                            Constants.clientIdKey,
                            OAuth.clientId,
                            Constants.redirectKey,
                            Constants.redirectUri(getString(R.string.scheme_name))
                        )
                    )
                )
                startActivity(intent)
            }
            else -> return false
        }

        return super.onOptionsItemSelected(item)
    }
    private fun initViewModel() {
        viewModel.usersList.observe(viewLifecycleOwner, Observer {
            adapter.bindData(it)
        })

        viewModel.accessToken.observe(viewLifecycleOwner, Observer {
            Log.d("oauthTest", "accessToken changed: " + it)
            viewModel.fetchUserList(it.access_token)
        })
    }

    private fun initAdapter() {
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = adapter
    }

    companion object {
        private val TAG = UsersListFragment::class.java.simpleName
    }
}
