package com.example.battlemanager.global.util

import com.example.battlemanager.domain.model.AbilityInfo
import com.example.battlemanager.domain.model.ItemInfo
import com.example.battlemanager.domain.model.RankStates
import kotlin.math.floor

object RealStatUtil {
    fun getAttack(
        level: Int,
        baseA: Int,
        ivA: Int,
        evA: Int,
        nature: Int,
        rank: RankStates,
        ability: AbilityInfo,
        item: ItemInfo
    ): Int {
        return floor(
            StatUtil.getA(level, baseA, ivA, evA, nature) * RankUtil.getBaseStatRankChange(
                rank.attack
            ) * AbilityUtil.getAttackCorrelation(ability) * ItemUtil.getAttackCorrelation(item)
        ).toInt()
    }

    fun getSpecialAttack(
        level: Int,
        baseB: Int,
        ivB: Int,
        evB: Int,
        nature: Int,
        rank: RankStates,
        ability: AbilityInfo,
        item: ItemInfo
    ): Int {
        return floor(
            StatUtil.getA(level, baseB, ivB, evB, nature) * RankUtil.getBaseStatRankChange(
                rank.specialAttack
            ) * AbilityUtil.getSpecialAttackCorrelation(ability) * ItemUtil.getSpecialAttackCorrelation(
                item
            )
        ).toInt()
    }
    /* 방, 특방에서 자폭 보정 제외*/
    fun getDefense(
        level: Int,
        baseC: Int,
        ivC: Int,
        evC: Int,
        nature: Int,
        rank: RankStates,
        ability: AbilityInfo,
        item: ItemInfo
    ): Int {
        return floor(
            StatUtil.getA(level, baseC, ivC, evC, nature) * RankUtil.getBaseStatRankChange(
                rank.defense
            ) * AbilityUtil.getDefenseCorrelation(ability) * ItemUtil.getDefenseCorrelation(item)
        ).toInt()
    }

    fun getSpecialDefense(
        level: Int,
        baseD: Int,
        ivD: Int,
        evD: Int,
        nature: Int,
        rank: RankStates,
        ability: AbilityInfo,
        item: ItemInfo,
        weather: String,
        type1: String,
        type2: String
    ): Int {
        return floor(
            StatUtil.getA(level, baseD, ivD, evD, nature) * RankUtil.getBaseStatRankChange(
                rank.special_defense
            ) * AbilityUtil.getSpecialDefenseCorrelation(ability) * ItemUtil.getSpecialDefenseCorrelation(
                item
            ) * mod(weather, type1, type2)
        ).toInt()
    }

    fun getSpeed(
        level: Int,
        baseS: Int,
        ivS: Int,
        evS: Int,
        nature: Int,
        rank: RankStates,
        ability: AbilityInfo,
        item: ItemInfo
    ): Int {
        return floor(
            StatUtil.getA(level, baseS, ivS, evS, nature) * RankUtil.getBaseStatRankChange(
                rank.speed
            ) * AbilityUtil.getSpeedCorrelation(ability) * ItemUtil.getSpeedCorrelation(item)
        ).toInt()
    }

    private fun mod(weather: String, type1: String, type2: String): Float {
        return if (weather == "모래바람") {
            if (type1 == "바위" || type2 == "바위")
                1.5f
            else
                1.0f
        } else
            1.0f
    }
}