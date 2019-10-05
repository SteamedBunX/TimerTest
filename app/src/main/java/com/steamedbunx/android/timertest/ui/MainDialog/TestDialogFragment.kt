package com.steamedbunx.android.timertest.ui.MainDialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.steamedbunx.android.timertest.R
import com.steamedbunx.android.timertest.databinding.TestDialogFragmentBinding
import com.steamedbunx.android.timertest.ui.main.MainViewModel
import com.steamedbunx.android.timertest.ui.main.MainViewModelFactory
import kotlinx.android.synthetic.main.test_dialog_fragment.*

class TestDialogFragment : DialogFragment() {

    lateinit var binding: TestDialogFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory

    companion object {
        @JvmStatic
        fun newInstance() =
            TestDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.test_dialog_fragment, container, true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelFactory = MainViewModelFactory(requireContext())
        viewModel = requireActivity().run{
            ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        }
        binding.editText.setText(viewModel.dialogText.value)
        binding.button.setOnClickListener { changeDialog()
                                            dismiss()}
    }

    private fun changeDialog(){
        viewModel.setDialogText(binding.editText.text.toString())
    }
}
