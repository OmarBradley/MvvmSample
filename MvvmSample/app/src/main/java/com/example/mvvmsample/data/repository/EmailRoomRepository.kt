package com.example.mvvmsample.data.repository

import com.example.mvvmsample.data.persistence.EmailDao
import com.example.mvvmsample.data.persistence.EmailRoomMapper
import com.example.mvvmsample.domain.repository.EmailRepository
import com.example.mvvmsample.entity.EmailEntity
import io.reactivex.Completable
import io.reactivex.Single

class EmailRoomRepository(
        private val emailDao: EmailDao
) : EmailRepository {

    override fun deleteAllEmail(): Completable =
            Completable.fromAction {
                emailDao.deleteAllEmail()
            }

    override fun saveEmail(emailEntity: EmailEntity): Completable =
            Completable.fromAction {
                emailDao.insertEmail(EmailRoomMapper.convertEntityToData(emailEntity))
            }

    override fun getEmail(email: String): Single<EmailEntity> =
            emailDao.getEmail(email)
                    .map { EmailRoomMapper.convertDataToEntity(it) }

    override fun getAllEmails(): Single<List<EmailEntity>> =
            emailDao.getAllEmails()
                    .map { emails ->
                        emails.map { EmailRoomMapper.convertDataToEntity(it) }
                    }

}
