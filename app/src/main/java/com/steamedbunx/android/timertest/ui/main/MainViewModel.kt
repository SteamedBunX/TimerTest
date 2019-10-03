package com.steamedbunx.android.timertest.ui.main

import android.content.Context
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.steamedbunx.android.timertest.data.Alarm
import com.steamedbunx.android.timertest.data.Item

class MainViewModel(val context: Context) : ViewModel() {
    // A mock model of eggroll item
    val item: Item = Item("Pork Egg Rolls","pork_egg_rolls.webp", 30)
    // LiveData for the Alarm interface to show

    val handler = Handler()
    val runnable:Runnable = object : Runnable {
        override fun run() {
            tick()
            handler.postDelayed(this, 1000)
        }
    }
    var alarm = Alarm(item,context)

    private val _alarm_changed = MutableLiveData<Boolean>()
    val alarm_changed: LiveData<Boolean>
        get() = _alarm_changed

    init{
        resetAlarm()
        handler.postDelayed(runnable,1000)
    }

    fun resetAlarm(){
        _alarm_changed.value = true
        alarm = Alarm(item,context)
    }

    fun changeComplete(){
        _alarm_changed.value = false
    }

    fun tickDown(){
        alarm.tickDown()
        if(alarm.isFinished)
        {
            resetAlarm()
        }
        _alarm_changed.value = true
    }

    private fun tick(){
        alarm.tick()
        if(alarm.isFinished)
        {
            resetAlarm()
        }
        _alarm_changed.value = true
    }

    // once the program is turned off remove the repeating task
    fun onDestroy(){
        handler.removeCallbacks(runnable)
    }

}
