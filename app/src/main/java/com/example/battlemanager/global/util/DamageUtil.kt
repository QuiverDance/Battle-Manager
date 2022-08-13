package com.example.battlemanager.global.util

import android.util.Log
import com.example.battlemanager.domain.model.MoveInfo
import com.example.battlemanager.domain.model.Pokemon
import com.example.battlemanager.global.constant.StatusAbnormality
import kotlin.math.floor

object DamageUtil {
    /*
    도우미, 충전, 흙놀이, 물놀이 미반영
     */
    fun getDamage(
        myPokemon: Pokemon,
        opponentPokemon: Pokemon,
        move: MoveInfo,
        weather: String,
        isCritical: Boolean,
        randType: Int
    ): Int {
        if (move.type == "변화")
            return 0

        val attackRealStat = getAttackRealStat(move, myPokemon)
        val defenseRealStat = getDefenseRealStat(move, opponentPokemon, weather)
        val criticalCoefficient = getCriticalCoefficient(myPokemon, isCritical)
        val randomNumber = getRandomNumber(randType)
        val typeCorrelation = getTypeCorrelation(move, myPokemon)

        return floor(
            floor(
                (floor(
                    floor(
                        floor(
                            floor(
                                floor(myPokemon.level * 0.4) * PowerUtil.getPower(
                                    move.power,
                                    myPokemon.item,
                                    myPokemon.ability,
                                    opponentPokemon.ability
                                ) * attackRealStat * 0.02
                            ) / defenseRealStat
                        )
                    ) * mod1(move, myPokemon, weather)
                ) + 2) * criticalCoefficient * mod2(myPokemon) * randomNumber * 0.01
            ) * typeCorrelation
                    * TypeUtil.getTypeCorrelation(move.type, opponentPokemon.pokemonInfo.type1)
                    * TypeUtil.getTypeCorrelation(move.type, opponentPokemon.pokemonInfo.type2)
                    * mod3(move, myPokemon, opponentPokemon)
        ).toInt()
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

    private fun getCriticalCoefficient(pokemon: Pokemon, isCritical: Boolean): Float {
        return if (isCritical) {
            if (pokemon.ability.name == "스나이퍼")
                2.25f
            else
                1.5f
        } else
            1f
    }

    private fun getRandomNumber(randType: Int): Int {
        return when (randType) {
            0 -> 85
            else -> 100
        }
    }

    private fun getTypeCorrelation(move: MoveInfo, pokemon: Pokemon): Float {
        return if (move.type == pokemon.pokemonInfo.type1 || move.type == pokemon.pokemonInfo.type2)
            if (pokemon.ability.name == "적응력")
                2f
            else
                1.5f
        else
            1f
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

    /*
    메트로놈 효과 미반영
     */
    private fun mod2(pokemon: Pokemon): Float {
        return if (pokemon.item.name == "생명의구슬")
            1.3f
        else
            1f
    }

    /*
    나무열매 반감 효과 미반영
     */
    private fun mod3(move: MoveInfo, myPokemon: Pokemon, opponentPokemon: Pokemon): Float {
        val abilityEffect1 =
            if (opponentPokemon.ability.name == "하드록" || opponentPokemon.ability.name == "필터") {
                if (TypeUtil.getTypeCorrelation(
                        move.type,
                        opponentPokemon.pokemonInfo.type1
                    ) == 2f || TypeUtil.getTypeCorrelation(
                        move.type,
                        opponentPokemon.pokemonInfo.type1
                    ) == 2f
                )
                    0.75f
                else
                    1f
            } else
                1f

        val itemEffect = if (myPokemon.item.name == "달인의띠") {
            if (TypeUtil.getTypeCorrelation(
                    move.type,
                    opponentPokemon.pokemonInfo.type1
                ) == 2f || TypeUtil.getTypeCorrelation(
                    move.type,
                    opponentPokemon.pokemonInfo.type2
                ) == 2f
            )
                1.2f
            else
                1f
        } else
            1f

        val abilityEffect2 = if (myPokemon.ability.name == "색안경") {
            if (TypeUtil.getTypeCorrelation(
                    move.type,
                    opponentPokemon.pokemonInfo.type1
                ) == 0.5f || TypeUtil.getTypeCorrelation(
                    move.type,
                    opponentPokemon.pokemonInfo.type2
                ) == 0.5f
            )
                2f
            else
                1f
        } else
            1f

        return floor(abilityEffect1 * itemEffect * abilityEffect2)
    }
}