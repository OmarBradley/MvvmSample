package com.example.mvvmsample.domain.usecase

import com.example.mvvmsample.domain.repository.EmailRepository
import com.example.mvvmsample.entity.EmailEntity
import io.reactivex.Single

class GetEmailUseCase(
        val emailRepository: EmailRepository
) {

    fun getEmail(email: String): Single<EmailEntity> =
            emailRepository.getEmail(email)

    fun getAllEmails(): Single<List<EmailEntity>> =
            emailRepository.getAllEmails()

}
