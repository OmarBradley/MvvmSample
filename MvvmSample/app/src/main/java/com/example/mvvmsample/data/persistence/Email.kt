package com.example.mvvmsample.data.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "email")
data class Email(

        @PrimaryKey
        private var email: String
)
