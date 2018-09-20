package com.example.mvvmsample.base.di

import com.example.mvvmsample.presentation.main.MainActivity
import com.example.mvvmsample.presentation.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun mainActivity(): MainActivity

}