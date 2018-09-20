package com.example.mvvmsample.data.module

import android.content.Context
import androidx.room.Room
import com.example.mvvmsample.base.di.ApplicationContext
import com.example.mvvmsample.data.persistence.EmailDao
import com.example.mvvmsample.data.persistence.EmailDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDataModule {

    @Provides
    @Singleton
    fun provideEmailDao(emailDatabase: EmailDatabase): EmailDao =
            emailDatabase.emailDao()

    @Provides
    @Singleton
    fun provideEmailDatabase(@ApplicationContext context: Context): EmailDatabase =
            Room.databaseBuilder(context,
                    EmailDatabase::class.java, "email_database.db")
                    .build()

}
