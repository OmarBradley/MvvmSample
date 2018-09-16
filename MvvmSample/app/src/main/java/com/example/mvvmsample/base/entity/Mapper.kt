package com.example.mvvmsample.base.entity

interface Mapper<ENTITY, DATA> {

    fun convertDataToEntity(data: DATA): ENTITY

    fun convertEntityToData(entity: ENTITY): DATA

}
