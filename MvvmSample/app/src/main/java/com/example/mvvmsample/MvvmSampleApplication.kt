package com.example.mvvmsample

import com.example.mvvmsample.base.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.realm.Realm

class MvvmSampleApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent.builder()
                    .application(this)
                    .build()

}
