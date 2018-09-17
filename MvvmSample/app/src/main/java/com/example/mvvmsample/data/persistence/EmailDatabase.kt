package com.example.mvvmsample.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmailRoomObject::class], version = 1)
abstract class EmailDatabase : RoomDatabase() {

    abstract fun emailDao(): EmailDao

}
