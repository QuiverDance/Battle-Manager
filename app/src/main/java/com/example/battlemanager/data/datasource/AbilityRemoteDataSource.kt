package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.model.AbilityResponse

interface AbilityRemoteDataSource {
    suspend fun getAbility(id : Int) : AbilityResponse
    suspend fun getAbilities() : List<AbilityResponse>
}