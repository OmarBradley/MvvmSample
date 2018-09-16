package com.example.mvvmsample.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class EmailRealmObject(

        @PrimaryKey
        var email: String = ""

) : RealmObject()
