package com.steamedbunx.android.timertest.util.notificationControl

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.steamedbunx.android.timertest.R

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null && intent.action != null) {
            // 1
            if (intent.action!!.equals(
                    context.getString(R.string.ACTION_FRY_COMPLETE),
                    ignoreCase = true
                )
            ) {
                if (intent.extras != null) {
                    val notiHelper = NotificationHelper.getInstance()
                    notiHelper.showNotification(context)
                }
            }
        }
    }


}