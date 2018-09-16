package com.example.mvvmsample

import com.example.mvvmsample.base.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MvvmSampleApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent.builder()
                    .application(this)
                    .build()

}
