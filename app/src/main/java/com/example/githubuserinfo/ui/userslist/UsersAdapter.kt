package com.example.githubuserinfo.ui.userslist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserinfo.R
import com.example.githubuserinfo.data.User
import com.example.githubuserinfo.ui.common.DialogUtils
import javax.inject.Inject

class UsersAdapter @Inject constructor(
    private val activity: AppCompatActivity
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private var usersList = arrayListOf<User>()

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val userName: TextView = view.findViewById(R.id.user_name)
        private val userThumbnail: ImageView = view.findViewById(R.id.user_thumbnail)

        fun bind(user: User) {
            userName.text = user.login
            Glide.with(activity).load(user.avatar_url).override(100).into(userThumbnail)
        }
    }

    fun bindData(users: List<User>) {
        usersList.clear()
        usersList.addAll(users)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.layout_user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(usersList[position])
        holder.itemView.setOnClickListener {
            DialogUtils.showUserInfoDialog(activity, usersList[position].login)
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    companion object {
        private val TAG = UsersAdapter::class.java.simpleName
    }
}
