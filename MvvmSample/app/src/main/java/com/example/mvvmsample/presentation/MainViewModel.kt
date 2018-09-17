package com.example.mvvmsample.presentation

import android.graphics.Color
import androidx.annotation.ColorInt
import com.example.mvvmsample.base.presentation.BaseViewModel
import com.example.mvvmsample.domain.usecase.CheckEmailUseCase
import com.example.mvvmsample.domain.usecase.DeleteEmailUseCase
import com.example.mvvmsample.domain.usecase.GetEmailUseCase
import com.example.mvvmsample.domain.usecase.SaveEmailUseCase
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MainViewModel(
        val checkEmailUseCase: CheckEmailUseCase,
        val deleteEmailUseCase: DeleteEmailUseCase,
        val getEmailUseCase: GetEmailUseCase,
        val saveEmailUseCase: SaveEmailUseCase
) : BaseViewModel() {

    val validationMessage: BehaviorSubject<String> = BehaviorSubject.createDefault("이메일이 비어 있습니다")

    @ColorInt
    val emailInputBackgroundColor: BehaviorSubject<Int> = BehaviorSubject.createDefault(Color.RED)

    val isEnableSubmitEmailButton: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    val checkedEmail: PublishSubject<String> = PublishSubject.create()

    fun checkEmail(email: String): Disposable =
            checkEmailUseCase.checkEmail(email)
                    .subscribe { emailValidState ->
                        validationMessage.onNext(emailValidState.message)
                        emailInputBackgroundColor.onNext(emailValidState.captionColor)
                        isEnableSubmitEmailButton.onNext(emailValidState.isEnableButton)
                    }


}
