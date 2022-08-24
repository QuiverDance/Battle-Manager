package com.example.battlemanager.presentation.global.util

import com.example.battlemanager.domain.model.AbilityInfo

object AbilityUtil {
    fun getAttackCorrelation(abilityInfo: AbilityInfo) : Float{
        return when(abilityInfo.name){
            else -> 1.0f
        }
    }
    fun getSpecialAttackCorrelation(abilityInfo: AbilityInfo) : Float{
        return when(abilityInfo.name){
            else -> 1.0f
        }
    }
    fun getDefenseCorrelation(abilityInfo: AbilityInfo) : Float{
        return when(abilityInfo.name){
            else -> 1f
        }
    }
    fun getSpecialDefenseCorrelation(abilityInfo: AbilityInfo) : Float{
        return when(abilityInfo.name){
            else -> 1f
        }
    }
    fun getSpeedCorrelation(abilityInfo: AbilityInfo) : Float{
        return when(abilityInfo.name){
            else -> 1f
        }
    }
    fun getMyPowerCorrelation(abilityInfo: AbilityInfo) : Float{
        return when(abilityInfo.name){
            else -> 1f
        }
    }
    fun getOpponentPowerCorrelation(abilityInfo: AbilityInfo) : Float{
        return when(abilityInfo.name){
            else -> 1f
        }
    }
}