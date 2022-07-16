package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.api.AbilityService
import com.example.battlemanager.data.model.AbilityResponse

class AbilityRemoteDataSourceImpl(private val service: AbilityService) : AbilityRemoteDataSource {
    override suspend fun getAbility(id: Int): AbilityResponse {
        return service.getAbility(id)
    }

    override suspend fun getAbilities(): List<AbilityResponse> {
        return service.getAbilities()
    }

}