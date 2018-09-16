package com.example.mvvmsample.domain.usecase

import com.example.mvvmsample.domain.repository.EmailRepository
import com.example.mvvmsample.entity.Email
import io.reactivex.Completable

class SaveEmailUseCase(
        val emailRepository: EmailRepository
) {

    fun saveEmail(email: Email): Completable =
            emailRepository.saveEmail(email)

}
