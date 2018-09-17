package com.example.mvvmsample.base.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    @ApplicationContext
    abstract fun bindContext(application: Application): Context

}
