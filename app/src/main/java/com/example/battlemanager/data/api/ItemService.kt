package com.example.battlemanager.data.api

import com.example.battlemanager.data.model.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemService {
    @GET("item/one")
    fun getMoveForName(@Query("name") name: String): Call<ItemResponse>

    @GET("item/all")
    fun getMoves(): Call<List<ItemResponse>>
}