package com.example.mvvmsample.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsample.domain.usecase.CheckEmailUseCase
import com.example.mvvmsample.domain.usecase.DeleteEmailUseCase
import com.example.mvvmsample.domain.usecase.GetEmailUseCase
import com.example.mvvmsample.domain.usecase.SaveEmailUseCase

class MainViewModelFactory(
        private val checkEmailUseCase: CheckEmailUseCase,
        private val deleteEmailUseCase: DeleteEmailUseCase,
        private val getEmailUseCase: GetEmailUseCase,
        private val saveEmailUseCase: SaveEmailUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass.getConstructor(CheckEmailUseCase::class.java, DeleteEmailUseCase::class.java, GetEmailUseCase::class.java, SaveEmailUseCase::class.java)
                    .newInstance(checkEmailUseCase, deleteEmailUseCase, getEmailUseCase, saveEmailUseCase)

}
