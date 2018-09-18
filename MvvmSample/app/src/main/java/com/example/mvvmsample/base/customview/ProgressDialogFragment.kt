package com.example.mvvmsample.base.customview

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mvvmsample.R
import com.example.mvvmsample.base.presentation.BaseActivity

class ProgressDialogFragment : DialogFragment() {

    companion object {

        private var dialogInstance: ProgressDialogFragment? = null

        fun showDialog(activity: BaseActivity) {
            dialogInstance = ProgressDialogFragment()
            dialogInstance?.show(activity.supportFragmentManager, "ProgressDialogFragment")
        }

        fun dismissDialog() {
            dialogInstance?.dismiss()
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireActivity())
                    .apply {
                        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_fragment_progress, null)
                        setView(view)
                    }
                    .create()

}
