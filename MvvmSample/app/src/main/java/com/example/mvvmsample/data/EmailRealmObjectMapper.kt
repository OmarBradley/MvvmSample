package com.example.mvvmsample.data

import com.example.mvvmsample.base.entity.Mapper
import com.example.mvvmsample.entity.Email

object EmailRealmObjectMapper : Mapper<Email, EmailRealmObject> {

    override fun convertDataToEntity(data: EmailRealmObject): Email =
            Email(data.email)

    override fun convertEntityToData(entity: Email): EmailRealmObject =
            EmailRealmObject(email = entity.email)

}
