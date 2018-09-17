package com.example.mvvmsample.base.presentation

import io.reactivex.disposables.Disposable

operator fun AutoClearedDisposable.plusAssign(disposable: Disposable) =
        this.add(disposable)
