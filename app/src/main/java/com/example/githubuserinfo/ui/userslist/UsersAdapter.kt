package com.example.githubuserinfo.ui.userslist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserinfo.R
import com.example.githubuserinfo.data.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private var usersList: List<User> = ArrayList(0)

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.user_name)
        val userThumbnail: ImageView = view.findViewById(R.id.user_thumbnail)
    }

    fun bindData(users: List<User>) {
        usersList = ArrayList(users)
        Log.d(TAG, "bindData usersList: $usersList")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder called")
        holder.userName.text = usersList[position].login
        // TODO: thumbnail 지정
//        holder.itemView.setOnClickListener {
//
//        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    companion object {
        private val TAG = UsersAdapter::class.java.simpleName
    }
}
