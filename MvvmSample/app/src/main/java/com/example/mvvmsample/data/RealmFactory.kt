package com.example.mvvmsample.data

import io.realm.Realm
import io.realm.RealmConfiguration

fun realmInstance(): Realm = Realm.getInstance(createRealmConfiguration())

private fun createRealmConfiguration(): RealmConfiguration =
        RealmConfiguration.Builder()
                .build()
