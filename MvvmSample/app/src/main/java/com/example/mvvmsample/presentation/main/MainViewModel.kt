package com.example.mvvmsample.presentation.main

import android.graphics.Color
import androidx.annotation.ColorInt
import com.example.mvvmsample.base.presentation.BaseViewModel
import com.example.mvvmsample.base.viewdata.ToastStateData
import com.example.mvvmsample.domain.usecase.CheckEmailUseCase
import com.example.mvvmsample.domain.usecase.DeleteEmailUseCase
import com.example.mvvmsample.domain.usecase.GetEmailUseCase
import com.example.mvvmsample.domain.usecase.SaveEmailUseCase
import com.example.mvvmsample.entity.EmailEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MainViewModel(
        private val checkEmailUseCase: CheckEmailUseCase,
        private val deleteEmailUseCase: DeleteEmailUseCase,
        private val getEmailUseCase: GetEmailUseCase,
        private val saveEmailUseCase: SaveEmailUseCase
) : BaseViewModel() {

    val validationMessageText: BehaviorSubject<String> = BehaviorSubject.createDefault("이메일이 비어 있습니다")

    @ColorInt
    val validationMessageTextColor: BehaviorSubject<Int> = BehaviorSubject.createDefault(Color.GREEN)

    @ColorInt
    val emailInputBackgroundColor: BehaviorSubject<Int> = BehaviorSubject.createDefault(Color.RED)

    val isEnableSubmitEmailButton: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    val isShowLoadingDialog: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    val toastState: PublishSubject<ToastStateData> = PublishSubject.create()

    val storedEmailText: BehaviorSubject<String> = BehaviorSubject.createDefault("")

    fun checkEmail(email: String): Disposable =
            checkEmailUseCase.checkEmail(email)
                    .subscribe { emailValidState ->
                        validationMessageText.onNext(emailValidState.message)
                        emailInputBackgroundColor.onNext(emailValidState.captionColor)
                        isEnableSubmitEmailButton.onNext(emailValidState.isEnableButton)
                        validationMessageTextColor.onNext(emailValidState.captionColor)
                    }

    fun submitEmail(email: String): Disposable =
            checkDuplicateEmail(email)
                    .doOnSubscribe { isShowLoadingDialog.onNext(true) }
                    .concatWith(saveEmail(email))
                    .andThen(getEmails())
                    .subscribe({ totalEmail ->
                        isShowLoadingDialog.onNext(false)
                        toastState.onNext(ToastStateData(true, "이메일이 저장되었습니다"))
                        storedEmailText.onNext(totalEmail)
                    }, { e ->
                        isShowLoadingDialog.onNext(false)
                        toastState.onNext(ToastStateData(true, e.localizedMessage))
                    })


    private fun checkDuplicateEmail(email: String): Completable =
            getEmailUseCase.checkDuplicateEmail(email)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        isShowLoadingDialog.onNext(false)
                        toastState.onNext(ToastStateData(true, "해당 이메일은 이미 존재하는 이메일입니다"))
                    }

    private fun saveEmail(email: String): Completable =
            saveEmailUseCase.saveEmail(emailEntity = EmailEntity(email))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        isShowLoadingDialog.onNext(false)
                        toastState.onNext(ToastStateData(true, it.message ?: ""))
                    }

    private fun getEmails(): Single<String> =
            getEmailUseCase.getAllEmails()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { it.joinToString(postfix = "\n") { it.email } }
                    .doOnError {
                        isShowLoadingDialog.onNext(false)
                        toastState.onNext(ToastStateData(true, it.message ?: ""))
                    }

    fun deleteEmail(): Disposable =
            deleteEmailUseCase.deleteAllEmail()
                    .doOnSubscribe { isShowLoadingDialog.onNext(true) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        isShowLoadingDialog.onNext(false)
                        toastState.onNext(ToastStateData(true, "모든 이메일 삭제에 성공하셨습니다"))
                        storedEmailText.onNext("")
                    }, { e ->
                        isShowLoadingDialog.onNext(false)
                        toastState.onNext(ToastStateData(true, e.message ?: ""))
                    })

}
