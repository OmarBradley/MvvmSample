package com.example.mvvmsample.presentation

import com.example.mvvmsample.base.presentation.BaseViewModel
import com.example.mvvmsample.base.presentation.BaseViewModelFactory
import com.example.mvvmsample.domain.usecase.CheckEmailUseCase

class MainViewModelFactory(
        private val checkEmailUseCase: CheckEmailUseCase
) : BaseViewModelFactory() {

    override fun createViewModel(): BaseViewModel = MainViewModel(checkEmailUseCase)

}
