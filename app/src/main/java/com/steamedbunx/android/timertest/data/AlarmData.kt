package com.steamedbunx.android.timertest.data

import android.graphics.Bitmap
import java.util.*

class AlarmData(val id: Int,
                val dishName: String,
                val timeTillAlarm: Long,
                val bigIconBitmap: Bitmap){
    var alarmTime:Long = 0
    init{
        alarmTime = timeTillAlarm + System.currentTimeMillis()
    }
}
