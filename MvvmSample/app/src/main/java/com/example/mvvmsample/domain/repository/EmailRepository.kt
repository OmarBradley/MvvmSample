package com.example.mvvmsample.domain.repository

import com.example.mvvmsample.entity.Email
import io.reactivex.Completable
import io.reactivex.Single

interface EmailRepository {

    fun deleteAllEmail(): Completable

    fun saveEmail(email: Email): Completable

    fun getEmail(email: String): Single<Email>

    fun getAllEmails(): Single<List<Email>>

}
