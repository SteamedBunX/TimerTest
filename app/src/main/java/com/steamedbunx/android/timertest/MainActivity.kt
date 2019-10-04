package com.steamedbunx.android.timertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.steamedbunx.android.timertest.ui.main.MainFragment
import com.steamedbunx.android.timertest.util.notificationControl.NotificationHelper

class MainActivity : AppCompatActivity() {

val notificationHelper = NotificationHelper.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationHelper.createDefaultNotificationChannel(applicationContext)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

    }



}
