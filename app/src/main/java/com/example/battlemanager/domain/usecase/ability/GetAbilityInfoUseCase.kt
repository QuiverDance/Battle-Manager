package com.example.battlemanager.domain.usecase.ability

import com.example.battlemanager.domain.model.AbilityInfo
import com.example.battlemanager.domain.repository.AbilityRepository

class GetAbilityInfoUseCase(private val repository: AbilityRepository) {
    suspend fun invoke(id: Int): AbilityInfo {
        return repository.getAbilityInfo(id)
    }
}