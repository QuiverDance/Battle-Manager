package com.example.battlemanager.domain.usecase.ability

import com.example.battlemanager.domain.model.AbilityInfo
import com.example.battlemanager.domain.repository.AbilityRepository

class GetAbilityInfoListUseCase(private val repository: AbilityRepository) {
    suspend fun invoke(): List<AbilityInfo> {
        return repository.getAbilityInfoList()
    }
}