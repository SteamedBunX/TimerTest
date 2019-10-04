package com.steamedbunx.android.timertest.util.notificationControl

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.steamedbunx.android.timertest.MainActivity
import com.steamedbunx.android.timertest.R

class NotificationHelper {

    var channelId = ""
    var currentId = 0

    companion object {
        private val helper: NotificationHelper =
            NotificationHelper()
        fun getInstance(): NotificationHelper {
            return helper
        }
    }

    fun showNotification(context: Context) {
        var builder = NotificationCompat.Builder(context, channelId).apply {
            // basic notification setting
            setSmallIcon(R.drawable.ic_alarm)
            setContentTitle("Frying Complete!")
            setContentText("Your EggRoll is done frying!")
            setDefaults(NotificationCompat.DEFAULT_SOUND)
            setPriority(NotificationCompat.PRIORITY_HIGH)
            setAutoCancel(true)

            // intent to go to the app when notification is clicked
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            setContentIntent(pendingIntent)
        }

        // display the notification
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(currentId, builder.build())
        currentId++
    }

    fun createDefaultNotificationChannel(context: Context) {
        createNotificationChannel(
            context, NotificationManagerCompat.IMPORTANCE_HIGH,
            false, context.getString(R.string.app_name),
            "Fry Completion Alarm Channel"
        )
    }

    fun createNotificationChannel(
        context: Context, importance: Int,
        showBadge: Boolean, name: String, description: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    // what the notification method should be, but since i'm just testing out some functionalities.
    // i won't be using this one.
    fun createDetailedNotification(context: Context, itemName: String,
                                    largeIcon: Bitmap,
                                    timeUntilNotification: Int){

        var builder = NotificationCompat.Builder(context, channelId).apply {
            // basic notification setting
            setSmallIcon(R.drawable.ic_alarm)
            setLargeIcon(largeIcon)
            setContentTitle("Frying Complete!")
            setContentText("${itemName} is done frying!")
            setPriority(NotificationCompat.PRIORITY_HIGH)
            setAutoCancel(true)

            // intent to go to the app when notification is clicked
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            setContentIntent(pendingIntent)
        }

    }

}