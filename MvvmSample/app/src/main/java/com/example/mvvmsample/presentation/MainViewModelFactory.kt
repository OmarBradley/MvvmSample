package com.example.mvvmsample.presentation

import com.example.mvvmsample.base.presentation.BaseViewModel
import com.example.mvvmsample.base.presentation.BaseViewModelFactory
import com.example.mvvmsample.domain.usecase.CheckEmailUseCase
import com.example.mvvmsample.domain.usecase.DeleteEmailUseCase
import com.example.mvvmsample.domain.usecase.GetEmailUseCase
import com.example.mvvmsample.domain.usecase.SaveEmailUseCase

class MainViewModelFactory(
        private val checkEmailUseCase: CheckEmailUseCase,
        private val deleteEmailUseCase: DeleteEmailUseCase,
        private val getEmailUseCase: GetEmailUseCase,
        private val saveEmailUseCase: SaveEmailUseCase
) : BaseViewModelFactory() {

    override fun createViewModel(): BaseViewModel =
            MainViewModel(checkEmailUseCase, deleteEmailUseCase, getEmailUseCase, saveEmailUseCase)

}
