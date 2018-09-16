package com.example.mvvmsample.base.di

import com.example.mvvmsample.presentation.MainActivity
import com.example.mvvmsample.presentation.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun mainActivity(): MainActivity

}