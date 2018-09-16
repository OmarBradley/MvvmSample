package com.example.mvvmsample.base.di

import android.app.Application
import com.example.mvvmsample.MvvmSampleApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApplicationModule::class),
    (AndroidSupportInjectionModule::class),
    (ActivityBindingModule::class)
])
interface ApplicationComponent : AndroidInjector<MvvmSampleApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): ApplicationComponent.Builder

        fun build(): ApplicationComponent

    }

}
