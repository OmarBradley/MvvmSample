package com.example.mvvmsample.presentation

import android.os.Bundle
import com.example.mvvmsample.R
import com.example.mvvmsample.base.presentation.AutoClearedDisposable
import com.example.mvvmsample.base.presentation.BaseActivity
import com.example.mvvmsample.base.presentation.plusAssign
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.afterTextChangeEvents
import kotlinx.android.synthetic.main.activity_main.*
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

        viewDisposable += viewModel.validationMessage
                .subscribe { validationMessage ->
                    textView_validationMessage.text = validationMessage
                }

        viewDisposable += viewModel.emailInputBackgroundColor
                .subscribe { backgroundColor ->
                    editText_emailInput.setBackgroundColor(backgroundColor)
                }

        viewDisposable += viewModel.isEnableSubmitEmailButton
                .subscribe { isEnable ->
                    button_submitEmail.isEnabled = isEnable
                }

        viewDisposable += button_submitEmail.clicks()
                .subscribe {

                }

    }

    override fun setLayoutId(): Int = R.layout.activity_main

}
