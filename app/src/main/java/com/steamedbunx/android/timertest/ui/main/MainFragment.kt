package com.steamedbunx.android.timertest.ui.main

import android.app.AlarmManager
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.steamedbunx.android.timertest.R
import com.steamedbunx.android.timertest.data.AlarmData
import com.steamedbunx.android.timertest.databinding.MainFragmentBinding
import com.steamedbunx.android.timertest.ui.MainDialog.TestDialogFragment
import com.steamedbunx.android.timertest.ui.SoundDialog.SoundDialogFragment
import com.steamedbunx.android.timertest.util.notificationControl.AlarmScheduler
import com.steamedbunx.android.timertest.util.notificationControl.NotificationHelper
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var notificationHelper: NotificationHelper
    private lateinit var alarmManager: AlarmManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        notificationHelper = NotificationHelper.getInstance()
        viewModelFactory = MainViewModelFactory(requireContext())
        viewModel = requireActivity().run {
            ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        }
        viewModel.alarmChanged.observe(this, Observer {
            if (it)
                binding.apply {
                    textTimer.text = viewModel.alarm.getTimerString()
                    Log.i("TIMERCHECK", viewModel.alarm.getTimerString())
                    progressBar.max = viewModel.alarm.getMaxProgress()
                    Log.i("TIMERCHECK", viewModel.alarm.getMaxProgress().toString())
                    progressBar.progress = viewModel.alarm.progress
                    Log.i("TIMERCHECK", viewModel.alarm.progress.toString())
                    itemName.text = viewModel.alarm.item?.name
                    Log.i("TIMERCHECK", viewModel.alarm.item?.name)
                    Log.i("TIMERCHECK", "Change involked")
                }
        })

        viewModel.dialogText.observe(this, Observer {
            textView_change_by_dialog.text = it
        })

        // onClickListeners
        binding.buttonTickDown.setOnClickListener {
            viewModel.tickDown()
        }
        binding.buttonRestart.setOnClickListener {
            viewModel.resetAlarm()
        }
        binding.buttonNotification.setOnClickListener {
            notificationHelper.showNotification(requireContext())
        }
        binding.buttonNotificationF.setOnClickListener {
            createAlarm()
        }
        binding.buttonPopup.setOnClickListener {
            showDialog()
        }
        binding.buttonSound.setOnClickListener {
            showSoundDialog()
        }


        // Dirty asf, just testing, don't do this kids
        activity?.assets?.open("pork_egg_rolls.webp").use {
            val drawable = Drawable.createFromStream(it, null)
            binding.imageView.setImageDrawable(drawable)
        }
    }

    fun createAlarm() {
        val alarmScheduler = AlarmScheduler()
        val alarmData = AlarmData(1, "Egg Roll", 10000)
        val pending = alarmScheduler.createPendingIntent(requireContext(), alarmData)
        alarmScheduler.scheduleAlarm(alarmData, pending, alarmManager)
    }

    fun showDialog() {
        val dialog = TestDialogFragment.newInstance()
        dialog.show(requireFragmentManager(), "test_dialog_fragment")
    }

    fun showSoundDialog(){
        val dialog = SoundDialogFragment.newInstance()
        dialog.show(requireFragmentManager(),"sound_dialog_fragment")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
