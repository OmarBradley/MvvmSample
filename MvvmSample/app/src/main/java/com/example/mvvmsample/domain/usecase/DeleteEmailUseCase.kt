package com.example.mvvmsample.domain.usecase

import com.example.mvvmsample.domain.repository.EmailRepository
import io.reactivex.Completable

class DeleteEmailUseCase(
        val emailRepository: EmailRepository
) {

    fun deleteAllEmail(): Completable =
            emailRepository.deleteAllEmail()

}
