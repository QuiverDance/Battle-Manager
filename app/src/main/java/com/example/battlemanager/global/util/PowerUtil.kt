package com.example.battlemanager.global.util

import com.example.battlemanager.domain.model.AbilityInfo
import com.example.battlemanager.domain.model.ItemInfo
import kotlin.math.floor

object PowerUtil {
    /*
    도우미, 충전, 흙놀이, 물놀이 미반영
     */
    fun getPower(
        power: Int,
        item: ItemInfo,
        myAbility: AbilityInfo,
        opponentAbility: AbilityInfo
    ): Int {
        return floor(
            power * ItemUtil.getPowerCorrelation(item) * AbilityUtil.getMyPowerCorrelation(
                myAbility
            ) * AbilityUtil.getOpponentPowerCorrelation(opponentAbility)
        ).toInt()
    }
}