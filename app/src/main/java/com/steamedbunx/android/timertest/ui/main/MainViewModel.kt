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
    val item: Item = Item("Pork Egg Rolls", "pork_egg_rolls.webp", 30)

    // the Alarm
    val handler = Handler()
    val runnable: Runnable = object : Runnable {
        override fun run() {
            tick()
            handler.postDelayed(this, 1000)
        }
    }
    var alarm = Alarm(item, context)

    // LiveData for the Alarm interface to show
    private val _alarmChanged = MutableLiveData<Boolean>()
    val alarmChanged: LiveData<Boolean>
        get() = _alarmChanged

    // LiveData to test the Dialog Fragment
    private val _dialogText = MutableLiveData<String>()
    val dialogText: LiveData<String>
        get() = _dialogText

    //Start the Alarm
    init {
        setDialogText("Default Text")
        resetAlarm()
        handler.postDelayed(runnable, 1000)
    }

    // region Alarm Manipulation

    //reset the Alarm's value for display
    fun resetAlarm() {
        _alarmChanged.value = true
        alarm = Alarm(item, context)
    }

    fun changeComplete() {
        _alarmChanged.value = false
    }

    fun tickDown() {
        alarm.tickDown()
        if (alarm.isFinished) {
            resetAlarm()
        }
        _alarmChanged.value = true
    }

    private fun tick() {
        alarm.tick()
        if (alarm.isFinished) {
            resetAlarm()
        }
        _alarmChanged.value = true
    }

    // endregion

    // region Dialog Fragment Information passing
    fun setDialogText(text: String){
        _dialogText.value = text
    }
    // endregion

}
