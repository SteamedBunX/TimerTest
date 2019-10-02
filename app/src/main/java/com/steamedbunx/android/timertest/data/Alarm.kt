package com.steamedbunx.android.timertest.data

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.steamedbunx.android.timertest.R

class Alarm(val item: Item, private val context: Context) {


    // record how much time is left,
    // used to keep track of how much time left
    // also calculate the string for timer
    var timeLeft = item.timeInSec
    // used to track the progress bar's current progression
    var progress = 0
    var isFinished = false
    // Channel ID
//    companion object {
//        const val CHANNEL_ID = "Fryer Alarm"
//    }
    // count down goes down every second
    fun tick() {
        timeLeft--
        progress++
        if (timeLeft <= 0) {
            onFinish()
        }
    }

    // get the current time in a string format
    fun getTimerString(): String {
        val minuteUntilFinisheed : String =
            if (timeLeft / 60 >= 10) {
                (timeLeft / 60).toString()
            } else {
                "0" + timeLeft / 60
            }
        val secondsInMinuteUntilFinished = timeLeft % 60
        val timeLeftString = "$minuteUntilFinisheed : ${
        if (secondsInMinuteUntilFinished >= 10) {
            secondsInMinuteUntilFinished
        } else {
            "0" + secondsInMinuteUntilFinished
        }}"
        return timeLeftString
    }

    // used to set the max for progress bar
    fun getMaxProgress(): Int {
        return item.timeInSec
    }

    // this will not be in the actual product, but for learning sake
    fun tickDown() {
        timeLeft -= 10
        progress += 10
        if (timeLeft <= 0) {
            timeLeft = 0
            progress = item.timeInSec
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