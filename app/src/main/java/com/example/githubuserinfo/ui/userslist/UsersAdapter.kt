package com.example.githubuserinfo.ui.userslist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserinfo.R
import com.example.githubuserinfo.data.User

class UsersAdapter(private val context: Context, private val usersList: List<User>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

//    private var usersList: List<User> = ArrayList(0)

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.user_name)
        val userThumbnail: ImageView = view.findViewById(R.id.user_thumbnail)
    }

//    fun bindData(users: List<User>) {
//        usersList = ArrayList(users)
//        Log.d(TAG, "bindData usersList: $usersList")
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        Log.d(TAG, "onCreateViewHolder called")
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder called name: ${usersList[position].login}")
        holder.userName.text = usersList[position].login
        Glide.with(context).load(usersList[position].avatar_url).override(100).into(holder.userThumbnail)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    companion object {
        private val TAG = UsersAdapter::class.java.simpleName
    }
}
