package com.example.mvvmsample.presentation

import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsample.domain.usecase.CheckEmailUseCase
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideViewModel(activity: MainActivity, viewModelFactory: MainViewModelFactory): MainViewModel =
            ViewModelProviders.of(activity, viewModelFactory)[MainViewModel::class.java]

    @Provides
    fun provideViewModelFactory(checkEmailUseCase: CheckEmailUseCase): MainViewModelFactory =
            MainViewModelFactory(checkEmailUseCase)

    @Provides
    fun provideCheckEmailUseCase(): CheckEmailUseCase =
            CheckEmailUseCase()

}
