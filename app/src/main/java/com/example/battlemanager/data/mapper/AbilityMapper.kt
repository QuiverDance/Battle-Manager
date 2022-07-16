package com.example.battlemanager.data.mapper

import com.example.battlemanager.data.model.AbilityResponse
import com.example.battlemanager.domain.model.AbilityInfo

object AbilityMapper {
    fun mapperToAbilityInfo(response: AbilityResponse): AbilityInfo {
        return AbilityInfo(
            id = response.id,
            name = response.name,
            description = response.description
        )
    }

    fun mapperToAbilityResponse(abilityInfo: AbilityInfo): AbilityResponse {
        return AbilityResponse(
            id = abilityInfo.id,
            name = abilityInfo.name,
            description = abilityInfo.description
        )
    }

}