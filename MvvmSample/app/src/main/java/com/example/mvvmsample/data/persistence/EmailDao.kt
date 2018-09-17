package com.example.mvvmsample.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface EmailDao {

    @Query("DELETE FROM email")
    fun deleteAllEmail()

    @Insert
    fun insertEmail(emailRoomObject: EmailRoomObject)

    @Query("SELECT * FROM email WHERE email.email = :email")
    fun getEmail(email: String): Single<EmailRoomObject>

    @Query("SELECT * FROM email")
    fun getAllEmails(): Single<List<EmailRoomObject>>

}
