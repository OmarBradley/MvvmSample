package com.example.mvvmsample.data.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "email")
data class EmailRoomObject(

        @PrimaryKey
        var email: String
)
