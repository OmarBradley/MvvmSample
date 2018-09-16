package com.example.mvvmsample.base.presentation

import com.example.mvvmsample.base.presentation.AutoClearedDisposable
import io.reactivex.disposables.Disposable

operator fun AutoClearedDisposable.plusAssign(disposable: Disposable) =
        this.add(disposable)
