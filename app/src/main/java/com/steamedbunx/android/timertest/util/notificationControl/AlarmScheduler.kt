package com.steamedbunx.android.timertest.util.notificationControl

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.steamedbunx.android.timertest.R
import com.steamedbunx.android.timertest.data.AlarmData
import java.util.*
import java.util.Calendar.*

class AlarmScheduler {

    fun createPendingIntent(context: Context, alarmData: AlarmData): PendingIntent?{
        val intent = Intent(context.applicationContext, AlarmReceiver::class.java).apply {
            action = context.getString(R.string.ACTION_FRY_COMPLETE)
            type = "${alarmData.id}-${alarmData.dishName}-${alarmData.alarmTime} #"
            putExtra("id", alarmData.id)
        }
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun scheduleAlarm(alarmData: AlarmData, alarmIntent: PendingIntent?, alarmMgr: AlarmManager){
        alarmMgr.setExact(alarmData.id,alarmData.alarmTime,alarmIntent)
    }
}