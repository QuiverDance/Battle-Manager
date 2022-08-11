package com.example.battlemanager.global.util

import com.example.battlemanager.domain.model.MoveInfo
import com.example.battlemanager.domain.model.Pokemon
import com.example.battlemanager.global.constant.StatusAbnormality
import kotlin.math.floor

object DamageUtil {
    /*
    도우미, 충전, 흙놀이, 물놀이 미반영
     */
    fun getPower(
        myPokemon: Pokemon,
        opponentPokemon: Pokemon,
        move: MoveInfo,
        weather: String
    ): Int {
        if (move.type == "변화")
            return 0

        val attackRealStat = getAttackRealStat(move, myPokemon)
        val defenseRealStat = getDefenseRealStat(move, opponentPokemon, weather)

        return (floor(floor(
            floor(myPokemon.level * 0.4) * PowerUtil.getPower(
                move.power,
                myPokemon.item,
                myPokemon.ability,
                opponentPokemon.ability
            ) * attackRealStat * defenseRealStat * 0.02
        ) * mod1(move, myPokemon, weather)) + 2).toInt()
    }

    private fun getAttackRealStat(move: MoveInfo, pokemon: Pokemon): Int {
        return if (move.type == "물리") RealStatUtil.getAttack(
            pokemon.level,
            pokemon.pokemonInfo.baseStats.A,
            pokemon.individualValues.A,
            pokemon.effortValues.A,
            pokemon.nature,
            pokemon.rankStates,
            pokemon.ability,
            pokemon.item
        )
        else
            RealStatUtil.getSpecialAttack(
                pokemon.level,
                pokemon.pokemonInfo.baseStats.C,
                pokemon.individualValues.C,
                pokemon.effortValues.C,
                pokemon.nature,
                pokemon.rankStates,
                pokemon.ability,
                pokemon.item
            )
    }

    private fun getDefenseRealStat(move: MoveInfo, pokemon: Pokemon, weather: String): Int {
        return if (move.type == "물리") RealStatUtil.getDefense(
            pokemon.level,
            pokemon.pokemonInfo.baseStats.B,
            pokemon.individualValues.B,
            pokemon.effortValues.B,
            pokemon.nature,
            pokemon.rankStates,
            pokemon.ability,
            pokemon.item
        )
        else
            RealStatUtil.getSpecialDefense(
                pokemon.level,
                pokemon.pokemonInfo.baseStats.D,
                pokemon.individualValues.D,
                pokemon.effortValues.D,
                pokemon.nature,
                pokemon.rankStates,
                pokemon.ability,
                pokemon.item,
                weather,
                pokemon.pokemonInfo.type1,
                pokemon.pokemonInfo.type2
            )
    }
    /*
    리플렉터, 빛의장막, 더블배틀, 타오르는불꽃 효과 미반영
     */
    private fun mod1(move: MoveInfo, pokemon: Pokemon, weather: String): Float {
        val burnEffect =
            if (pokemon.statusAbnormality == StatusAbnormality.BURN && move.type == "물리") 0.5f else 1f
        val weatherEffect = if (weather == "쾌청") {
            if (move.type == "불꽃")
                1.5f
            else if (move.type == "물")
                0.5f
            else
                1f
        } else if (weather == "비") {
            if (move.type == "물")
                1.5f
            else if (move.type == "불꽃")
                0.5f
            else
                1f
        } else
            1f

        return floor(burnEffect * weatherEffect)
    }
}