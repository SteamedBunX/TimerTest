package com.steamedbunx.android.timertest.data

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.steamedbunx.android.timertest.R

class Alarm(val item: Item, private val context: Context) {

    var timeLeft = item.timeInMS
    var isFinished = false
    // Channel ID
//    companion object {
//        const val CHANNEL_ID = "Fryer Alarm"
//    }
    // count down goes down every second
    fun tick() {
        timeLeft--
        if (timeLeft <= 0) {
            onFinish()
        }
    }

    // when count down reaches 0
    // a notification will be sent
    // the isFinished will also be fliped to true to notify upper class
    private fun onFinish() {

        isFinished = true

//        var builder = NotificationCompat.Builder(context, CHANNEL_ID)
//            .setShortcutId("13")
//            .setSmallIcon(R.drawable.ic_alarm)
//            .setContentTitle("Frying Complete!")
//            .setContentText("Your ${item.name} is done frying!")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setAutoCancel(true)
//
//        with(NotificationManagerCompat.from(context)){
//            notify(notifyID,builder.build())
//        }
    }
}