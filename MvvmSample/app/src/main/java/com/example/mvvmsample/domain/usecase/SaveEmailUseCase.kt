package com.example.mvvmsample.domain.usecase

import com.example.mvvmsample.domain.repository.EmailRepository
import com.example.mvvmsample.entity.EmailEntity
import io.reactivex.Completable

class SaveEmailUseCase(
        private val emailRepository: EmailRepository
) {

    fun saveEmail(emailEntity: EmailEntity): Completable =
            emailRepository.saveEmail(emailEntity)

}
