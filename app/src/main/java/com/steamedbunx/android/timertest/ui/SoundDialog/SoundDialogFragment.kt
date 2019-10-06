package com.steamedbunx.android.timertest.ui.SoundDialog

import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.steamedbunx.android.timertest.R
import com.steamedbunx.android.timertest.databinding.SoundDialogFragmentBinding


class SoundDialogFragment : DialogFragment() {

    lateinit var soundPool:SoundPool
    var sound = 0
    lateinit var binding:SoundDialogFragmentBinding

    companion object {
        @JvmStatic
        fun newInstance() =
            SoundDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loadSoundPool()
        binding = DataBindingUtil.inflate(inflater, R.layout.sound_dialog_fragment, container, true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.assets?.open("pork_egg_rolls.webp").use {
            val drawable = Drawable.createFromStream(it, null)
            binding.imageView2.setImageDrawable(drawable)
        }

    }

    private fun loadSoundPool(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            soundPool = SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build()
        } else {
            soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        }
        sound = soundPool.load(requireContext(), R.raw.beeps, 1)
        Log.i("SoundPool", "Sound $sound LoadingStart" )
        soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
            soundPool.play(sampleId, 1F,1F,1,-1,1F)
            Log.i("SoundPool", "Sound $sound Loaded")
        }
    }

    private fun startSound(){

    }

    private fun stopSound(){
        soundPool.stop(sound)
        soundPool.release()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        stopSound()
    }
}
