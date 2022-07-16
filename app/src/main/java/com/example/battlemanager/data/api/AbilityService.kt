package com.example.battlemanager.data.api

import com.example.battlemanager.data.model.AbilityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AbilityService {
    @GET("/ability")
    suspend fun getAbility(@Query("id") id : Int): AbilityResponse

    @GET("/abilities")
    suspend fun getAbilities(): List<AbilityResponse>
}