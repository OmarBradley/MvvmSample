package com.example.mvvmsample.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmailDao {

    @Query("DELETE FROM email")
    fun deleteAllEmail()

    @Insert
    fun insertEmail(email: Email)

    @Query("SELECT * FROM email WHERE email.email = :email")
    fun getEmail(email: String)

    @Query("SELECT * FROM email")
    fun getAllEmails()

}
