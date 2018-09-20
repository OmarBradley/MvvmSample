package com.example.mvvmsample.presentation.main

import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsample.domain.usecase.CheckEmailUseCase
import com.example.mvvmsample.domain.usecase.DeleteEmailUseCase
import com.example.mvvmsample.domain.usecase.GetEmailUseCase
import com.example.mvvmsample.domain.usecase.SaveEmailUseCase
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideViewModel(activity: MainActivity, viewModelFactory: MainViewModelFactory): MainViewModel =
            ViewModelProviders.of(activity, viewModelFactory)[MainViewModel::class.java]

    @Provides
    fun provideViewModelFactory(
            checkEmailUseCase: CheckEmailUseCase,
            deleteEmailUseCase: DeleteEmailUseCase,
            getEmailUseCase: GetEmailUseCase,
            saveEmailUseCase: SaveEmailUseCase
    ): MainViewModelFactory =
            MainViewModelFactory(checkEmailUseCase, deleteEmailUseCase, getEmailUseCase, saveEmailUseCase)

}
