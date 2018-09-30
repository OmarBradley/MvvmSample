package com.example.mvvmsample.domain.usecase

import com.example.mvvmsample.domain.repository.EmailRepository
import com.example.mvvmsample.entity.EmailEntity
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Single

class GetEmailUseCase(
        private val emailRepository: EmailRepository
) {

    fun getAllEmails(): Single<List<EmailEntity>> =
            emailRepository.getAllEmails()

    fun checkDuplicateEmail(email: String): Completable =
            emailRepository.getEmail(email)
                    .map { CheckDuplicateEmailCase.DUPLICATE_EMAIL }
                    .onErrorReturn { CheckDuplicateEmailCase.NOT_DUPLICATE_EMAIL }
                    .flatMapCompletable { case ->
                        CompletableSource { source ->
                            when (case) {
                                CheckDuplicateEmailCase.DUPLICATE_EMAIL -> source.onError(Throwable("이메일이 중복 되었습니다"))
                                CheckDuplicateEmailCase.NOT_DUPLICATE_EMAIL -> source.onComplete()
                            }
                        }
                    }

    enum class CheckDuplicateEmailCase {
        DUPLICATE_EMAIL, NOT_DUPLICATE_EMAIL
    }

}
