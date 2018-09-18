package com.example.mvvmsample.presentation

import android.graphics.Color
import androidx.annotation.ColorInt
import com.example.mvvmsample.base.presentation.BaseViewModel
import com.example.mvvmsample.base.viewdata.ToastStateData
import com.example.mvvmsample.domain.usecase.CheckEmailUseCase
import com.example.mvvmsample.domain.usecase.DeleteEmailUseCase
import com.example.mvvmsample.domain.usecase.GetEmailUseCase
import com.example.mvvmsample.domain.usecase.SaveEmailUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MainViewModel(
        private val checkEmailUseCase: CheckEmailUseCase,
        private val deleteEmailUseCase: DeleteEmailUseCase,
        private val getEmailUseCase: GetEmailUseCase,
        private val saveEmailUseCase: SaveEmailUseCase
) : BaseViewModel() {

    val validationMessage: BehaviorSubject<String> = BehaviorSubject.createDefault("이메일이 비어 있습니다")

    @ColorInt
    val emailInputBackgroundColor: BehaviorSubject<Int> = BehaviorSubject.createDefault(Color.RED)

    val isEnableSubmitEmailButton: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    val isShowLoadingDialog: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    val toastState: BehaviorSubject<ToastStateData> = BehaviorSubject.createDefault(ToastStateData(false, ""))

    fun checkEmail(email: String): Disposable =
            checkEmailUseCase.checkEmail(email)
                    .subscribe { emailValidState ->
                        validationMessage.onNext(emailValidState.message)
                        emailInputBackgroundColor.onNext(emailValidState.captionColor)
                        isEnableSubmitEmailButton.onNext(emailValidState.isEnableButton)
                    }


    /*
    1. getEmailUseCase.getEmail 을 이용해 inputEmail 이 있는지 검사
        1) 있으면 에러 -> "이 이메일은 중복되었습니다"
        2) 없으면 2번으로 이동
    2. saveEmailUseCase.checkDuplicateEmail 을 이용해 inputEmail 을 Room DB에 저장
        1) 저장 실패시, "예기치 못한 에러"
        2) 저장 성공 시 3번으로 이동
    3. getEmailUseCase.getEmail 을 이용해 저장된 모든 이메일을 가져옴 
        1) 성공 시 -> textView 에 이메일을 뿌려줌
        2) 실패 시 -> "예기치 못한 에러"
     */

    fun checkDuplicateEmail(email: String): Disposable =
            getEmailUseCase.getEmail(email)
                    .doOnSubscribe { isShowLoadingDialog.onNext(true) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess {

                    }
                    .subscribe({
                        isShowLoadingDialog.onNext(false)
                        toastState.onNext(ToastStateData(true, "해당 이메일은 이미 존재하는 이메일입니다"))
                    }, { e ->

                    })


    /*getEmailUseCase.getEmail(email)
                    .doOnSubscribe { isShowLoadingDialog.onNext(true) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess {
                        isShowLoadingDialog.onNext(false)
                        toastState.onNext(ToastStateData(true, "해당 이메일은 이미 존재하는 이메일입니다"))
                    }
                    .onErrorResumeNext {
                        SingleSource { }
                    }
                    .concatWith {
                        saveEmailUseCase.checkDuplicateEmail(EmailEntity(email))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                    }
                    .doOnError {
                        isShowLoadingDialog.onNext(false)
                        toastState.onNext(ToastStateData(true, it.localizedMessage))
                    }
                    .subscribe {
                        isShowLoadingDialog.onNext(false)
                        toastState.onNext(ToastStateData(true, "${it.email} 이 저장되었습니다"))
                    }*/

}
