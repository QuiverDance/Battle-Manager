package com.example.battlemanager.global.util

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
}