package com.example.mvvmsample.domain.usecase

import android.graphics.Color
import androidx.annotation.ColorInt
import io.reactivex.Single
import java.util.regex.Pattern

class CheckEmailUseCase {

    val EMAIL_ADDRESS_REGEX = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+")

    fun checkEmail(checkEmail: String): Single<EmailValidState> =
            Single.just(checkEmail)
                    .map {
                        if (EMAIL_ADDRESS_REGEX.matcher(it).matches())
                            return@map EmailValidState.VALID
                        else
                            return@map EmailValidState.INVALID
                    }
                    .onErrorReturn { EmailValidState.ERROR }

}

enum class EmailValidState(
        val message: String,
        @ColorInt val captionColor: Int,
        val isEnableButton: Boolean
) {
    VALID("유효한 이메일입니다", Color.GREEN, true),
    INVALID("이메일이 유효하지 않습니다", Color.RED, false),
    ERROR("에러가 발생했습니다", Color.RED, false)
}
