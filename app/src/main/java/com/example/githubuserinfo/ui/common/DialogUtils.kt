package com.example.githubuserinfo.ui.common

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.example.githubuserinfo.R

object DialogUtils {
    @JvmStatic
    fun showUserInfoDialog(activity: Activity?, name: String) {
        activity ?: return

        val builder = AlertDialog.Builder(activity)
        builder.setMessage(activity.getString(R.string.dialog_info, name))
        builder.setPositiveButton("확인") { dialogInterface, _ -> dialogInterface.dismiss() }
        builder.show()
    }
}
