package com.example.jetpackcomponentsapp.repository

import com.example.jetpackcomponentsapp.room.CustomEntity

interface BaseRepository {

    fun  insert(customEntity: CustomEntity)

    fun  update(customEntity: CustomEntity)

    fun  delete(customEntity: CustomEntity)

    fun  deleteAll()
}