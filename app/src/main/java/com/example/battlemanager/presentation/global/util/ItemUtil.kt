package com.example.battlemanager.presentation.global.util

import com.example.battlemanager.domain.model.ItemInfo

object ItemUtil {
    fun getAttackCorrelation(itemInfo: ItemInfo): Float {
        return when (itemInfo.name) {
            else -> 1.0f
        }
    }

    fun getSpecialAttackCorrelation(itemInfo: ItemInfo): Float {
        return when (itemInfo.name) {
            else -> 1.0f
        }
    }

    fun getDefenseCorrelation(itemInfo: ItemInfo): Float {
        return when (itemInfo.name) {
            else -> 1f
        }
    }

    fun getSpecialDefenseCorrelation(itemInfo: ItemInfo): Float {
        return when (itemInfo.name) {
            else -> 1f
        }
    }

    fun getSpeedCorrelation(itemInfo: ItemInfo): Float{
        return when (itemInfo.name) {
            else -> 1f
        }
    }

    fun getPowerCorrelation(itemInfo: ItemInfo): Float{
        return when (itemInfo.name){
            else -> 1f
        }
    }

    fun toGetItemCategory() : List<String>{
        return listOf("나무열매", "배틀전용도구", "능력치상승도구", "기술강화도구", "플레이트", "주얼", "메가스톤", "원시회귀", "폼체인지")
    }
}