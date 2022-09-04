package com.example.battlemanager.presentation.global.util

import com.example.battlemanager.domain.model.AbilityInfo
import com.example.battlemanager.domain.model.ItemInfo
import com.example.battlemanager.domain.model.MoveInfo
import com.example.battlemanager.domain.model.Pokemon
import kotlin.math.floor

object PowerUtil {
    /*
    도우미, 충전, 흙놀이, 물놀이 미반영
     */
    fun getPower(
        move: MoveInfo,
        myPokemon: Pokemon,
        opponentPokemon: Pokemon
    ): Int {
        return floor(
            move.power * ItemUtil.getPowerCorrelation(myPokemon.item, move, myPokemon.pokemonInfo.name) * AbilityUtil.getMyPowerCorrelation(
                move, myPokemon, opponentPokemon
            ) * AbilityUtil.getOpponentPowerCorrelation(move, opponentPokemon)
        ).toInt()
    }
}