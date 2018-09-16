package com.example.mvvmsample.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Email::class], version = 1)
abstract class EmailDatabase : RoomDatabase() {

    abstract fun emailDao(): EmailDao

    companion object {

        @Volatile
        private var INSTANCE: EmailDatabase? = null

        fun getInstance(context: Context): EmailDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context)
                            .also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        EmailDatabase::class.java, "Email.db")
                        .build()
    }

}
