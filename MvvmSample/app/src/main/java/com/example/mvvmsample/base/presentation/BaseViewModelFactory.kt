package com.example.mvvmsample.base.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = createViewModel() as T

    protected abstract fun createViewModel(): BaseViewModel

}
