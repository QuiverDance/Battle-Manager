package com.example.battlemanager.domain.repository

import com.example.battlemanager.domain.model.AbilityInfo

interface AbilityRepository {
    suspend fun getAbilityInfo(id : Int) : AbilityInfo
    suspend fun getAbilityInfoList() : List<AbilityInfo>
}