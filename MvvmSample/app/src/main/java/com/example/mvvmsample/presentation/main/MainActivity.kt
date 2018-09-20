package com.example.mvvmsample.presentation.main

import android.content.res.ColorStateList
import android.os.Bundle
import com.example.mvvmsample.R
import com.example.mvvmsample.base.customview.ProgressDialogFragment
import com.example.mvvmsample.base.presentation.AutoClearedDisposable
import com.example.mvvmsample.base.presentation.BaseActivity
import com.example.mvvmsample.base.presentation.plusAssign
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.afterTextChangeEvents
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    private val viewDisposable: AutoClearedDisposable by lazy {
        AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false)
    }

    private val disposable: AutoClearedDisposable by lazy {
        AutoClearedDisposable(lifecycleOwner = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle += disposable
        lifecycle += viewDisposable

        viewDisposable += editText_emailInput.afterTextChangeEvents()
                .map { event ->
                    event.editable()?.toString() ?: ""
                }
                .subscribe { emailInput ->
                    disposable += viewModel.checkEmail(emailInput)
                }

        viewDisposable += viewModel.validationMessageText
                .subscribe { validationMessage ->
                    textView_validationMessage.text = validationMessage
                }

        viewDisposable += viewModel.validationMessageTextColor
                .subscribe { textColor ->
                    textView_validationMessage.setTextColor(textColor)
                }

        viewDisposable += viewModel.emailInputBackgroundColor
                .subscribe { backgroundColor ->
                    editText_emailInput.backgroundTintList = ColorStateList.valueOf(backgroundColor)
                }

        viewDisposable += viewModel.isEnableSubmitEmailButton
                .subscribe { isEnable ->
                    button_submitEmail.isEnabled = isEnable
                }

        viewDisposable += viewModel.isShowLoadingDialog
                .subscribe { isShow ->
                    if (isShow)
                        ProgressDialogFragment.showDialog(this)
                    else
                        ProgressDialogFragment.dismissDialog()
                }

        viewDisposable += viewModel.toastState
                .filter { it.isShow }
                .subscribe { toast(it.message) }

        viewDisposable += button_submitEmail.clicks()
                .subscribe {
                    disposable += viewModel.submitEmail(editText_emailInput.text.toString())
                }

        viewDisposable += viewModel.storedEmailText
                .subscribe {
                    textView_storedEmail.text = it
                }

        viewDisposable += button_deleteEmail.clicks()
                .subscribe {
                    disposable += viewModel.deleteEmail()
                }

    }

    override fun setLayoutId(): Int = R.layout.activity_main

}
