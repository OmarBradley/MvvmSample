package com.example.mvvmsample.data.persistence

import com.example.mvvmsample.base.entity.Mapper
import com.example.mvvmsample.entity.EmailEntity

object EmailRoomMapper : Mapper<EmailEntity, EmailRoomObject> {

    override fun convertDataToEntity(data: EmailRoomObject): EmailEntity =
            EmailEntity(data.email)

    override fun convertEntityToData(entity: EmailEntity): EmailRoomObject =
            EmailRoomObject(entity.email)
}
