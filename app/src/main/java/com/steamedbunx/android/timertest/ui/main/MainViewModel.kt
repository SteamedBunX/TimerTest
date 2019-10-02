package com.steamedbunx.android.timertest.ui.main

import android.app.AlarmManager
import android.content.Context
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.steamedbunx.android.timertest.data.Alarm
import com.steamedbunx.android.timertest.data.Item
import java.util.*

class MainViewModel(val context: Context) : ViewModel() {
    // A mock model of eggroll item
    val item: Item = Item("Port Egg Rolls","pork_egg_rolls", 30000)
    // LiveData for the Alarm interface to show

    val handler = Handler()
    var runnable:Runnable = object : Runnable {
        override fun run() {
            tick()
            handler.postDelayed(this, 1000)
        }
    }

    private val _alarm = MutableLiveData<Alarm>()
    val alarm: LiveData<Alarm>
        get() = _alarm

    init{
        resetAlarm()
        runnable = object : Runnable {
            override fun run() {
                tick()
                handler.postDelayed(this, 1000)
            }
        }
    }

    fun resetAlarm(){
        _alarm.value = Alarm(item, context)
    }

    fun tickDown(){
        _alarm.value?.tickDown()
    }

    private fun tick(){
        _alarm.value?.tick()
    }

    // once the program is turned off remove the repeating task
    fun onDestroy(){
        handler.removeCallbacks(runnable)
    }

}
