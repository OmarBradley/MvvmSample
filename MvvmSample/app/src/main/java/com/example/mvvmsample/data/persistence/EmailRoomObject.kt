package com.example.mvvmsample.data.persistence

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
        tableName = "email",
        indices = [Index(value = ["email"], unique = true)]
)
data class EmailRoomObject(
        var email: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
