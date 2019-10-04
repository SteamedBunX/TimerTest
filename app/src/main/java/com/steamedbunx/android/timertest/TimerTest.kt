package com.steamedbunx.android.timertest

import android.app.Application
import com.steamedbunx.android.timertest.util.notificationControl.NotificationHelper

class TimerTest: Application() {
    companion object {
        lateinit var instance: TimerTest
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val notificationHelper = NotificationHelper.getInstance()
        notificationHelper.createDefaultNotificationChannel(applicationContext)
    }

}