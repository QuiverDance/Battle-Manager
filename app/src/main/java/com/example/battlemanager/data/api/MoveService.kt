package com.example.battlemanager.data.api

import com.example.battlemanager.data.model.MoveResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoveService {
    @GET("move/one")
    fun getMoveForName(@Query("name") name: String): Call<MoveResponse>

    @GET("move/all")
    fun getMoves(): Call<List<MoveResponse>>

    @GET("move/all/name")
    fun getMoveNameList(): Call<List<String>>
}