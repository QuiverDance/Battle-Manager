package com.example.battlemanager.presentation.global.util

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
        attackRank: Int,
        ability: AbilityInfo,
        item: ItemInfo,
        statusAbnormality: Int,
        pokemonName: String
    ): Int {
        return floor(
            StatUtil.getA(level, baseA, ivA, evA, nature) * RankUtil.getBaseStatRankChange(
                attackRank
            ) * AbilityUtil.getAttackCorrelation(ability, statusAbnormality) * ItemUtil.getAttackCorrelation(item, pokemonName)
        ).toInt()
    }

    fun getSpecialAttack(
        level: Int,
        baseC: Int,
        ivC: Int,
        evC: Int,
        nature: Int,
        specialAttackRank: Int,
        ability: AbilityInfo,
        item: ItemInfo,
        weather: String,
        pokemonName: String
    ): Int {
        return floor(
            StatUtil.getC(level, baseC, ivC, evC, nature) * RankUtil.getBaseStatRankChange(
                specialAttackRank
            ) * AbilityUtil.getSpecialAttackCorrelation(ability, weather) * ItemUtil.getSpecialAttackCorrelation(
                item, pokemonName)
        ).toInt()
    }
    /* 방, 특방에서 자폭 보정 제외*/
    fun getDefense(
        level: Int,
        baseB: Int,
        ivB: Int,
        evB: Int,
        nature: Int,
        defenseRank: Int,
        ability: AbilityInfo,
        item: ItemInfo,
        statusAbnormality: Int,
        pokemonName: String
    ): Int {
        return floor(
            StatUtil.getB(level, baseB, ivB, evB, nature) * RankUtil.getBaseStatRankChange(
                defenseRank
            ) * AbilityUtil.getDefenseCorrelation(ability, statusAbnormality) * ItemUtil.getDefenseCorrelation(item, pokemonName)
        ).toInt()
    }

    fun getSpecialDefense(
        level: Int,
        baseD: Int,
        ivD: Int,
        evD: Int,
        nature: Int,
        specialDefenseRank: Int,
        ability: AbilityInfo,
        item: ItemInfo,
        weather: String,
        type1: String,
        type2: String,
        pokemonName: String
    ): Int {
        return floor(
            StatUtil.getD(level, baseD, ivD, evD, nature) * RankUtil.getBaseStatRankChange(
                specialDefenseRank
            ) * AbilityUtil.getSpecialDefenseCorrelation(ability) * ItemUtil.getSpecialDefenseCorrelation(
                item, pokemonName
            ) * mod(weather, type1, type2)
        ).toInt()
    }
    
    /*
    곡예, 불굴의마음 효과 미반영
     */
    fun getSpeed(
        level: Int,
        baseS: Int,
        ivS: Int,
        evS: Int,
        nature: Int,
        speedRank: Int,
        ability: AbilityInfo,
        item: ItemInfo,
        statusAbnormality: Int,
        weather: String,
        pokemonName: String
    ): Int {
        return floor(
            StatUtil.getS(level, baseS, ivS, evS, nature) * RankUtil.getBaseStatRankChange(
                speedRank
            ) * AbilityUtil.getSpeedCorrelation(ability, statusAbnormality, weather) * ItemUtil.getSpeedCorrelation(item, pokemonName)
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