package com.example.mvvmsample.domain.repository

import com.example.mvvmsample.entity.EmailEntity
import io.reactivex.Completable
import io.reactivex.Single

interface EmailRepository {

    fun deleteAllEmail(): Completable

    fun saveEmail(emailEntity: EmailEntity): Completable

    fun getEmail(email: String): Single<EmailEntity>

    fun getAllEmails(): Single<List<EmailEntity>>

}
