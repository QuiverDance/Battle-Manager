package com.example.battlemanager.presentation.global.util

import com.example.battlemanager.domain.model.ItemInfo
import com.example.battlemanager.domain.model.MoveInfo
import com.example.battlemanager.domain.model.Pokemon
import com.example.battlemanager.presentation.global.constant.Stat
import com.example.battlemanager.presentation.global.constant.StatusAbnormality
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
        if (move.category == "변화" || move.name == "미설정")
            return 0

        val attackRealStat = getAttackRealStat(move, myPokemon, weather)
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
                                    move,
                                    myPokemon,
                                    opponentPokemon
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

    private fun getAttackRealStat(move: MoveInfo, pokemon: Pokemon, weather: String): Int {
        return if (move.type == "물리") RealStatUtil.getAttack(
            pokemon.level,
            pokemon.pokemonInfo.baseStats[Stat.ATTACK],
            pokemon.individualValues[Stat.ATTACK],
            pokemon.effortValues[Stat.ATTACK],
            pokemon.nature,
            pokemon.rankStates[Stat.ATTACK],
            pokemon.ability,
            pokemon.item,
            pokemon.statusAbnormality,
            pokemon.pokemonInfo.name
        )
        else
            RealStatUtil.getSpecialAttack(
                pokemon.level,
                pokemon.pokemonInfo.baseStats[Stat.SPECIAL_ATTACK],
                pokemon.individualValues[Stat.SPECIAL_ATTACK],
                pokemon.effortValues[Stat.SPECIAL_ATTACK],
                pokemon.nature,
                pokemon.rankStates[Stat.SPECIAL_ATTACK],
                pokemon.ability,
                pokemon.item,
                weather,
                pokemon.pokemonInfo.name
            )
    }

    private fun getDefenseRealStat(move: MoveInfo, pokemon: Pokemon, weather: String): Int {
        return if (move.type == "물리") RealStatUtil.getDefense(
            pokemon.level,
            pokemon.pokemonInfo.baseStats[Stat.DEFENSE],
            pokemon.individualValues[Stat.DEFENSE],
            pokemon.effortValues[Stat.DEFENSE],
            pokemon.nature,
            pokemon.rankStates[Stat.DEFENSE],
            pokemon.ability,
            pokemon.item,
            pokemon.statusAbnormality,
            pokemon.pokemonInfo.name
        )
        else
            RealStatUtil.getSpecialDefense(
                pokemon.level,
                pokemon.pokemonInfo.baseStats[Stat.SPECIAL_DEFENSE],
                pokemon.individualValues[Stat.SPECIAL_DEFENSE],
                pokemon.effortValues[Stat.SPECIAL_DEFENSE],
                pokemon.nature,
                pokemon.rankStates[Stat.SPECIAL_DEFENSE],
                pokemon.ability,
                pokemon.item,
                weather,
                pokemon.pokemonInfo.type1,
                pokemon.pokemonInfo.type2,
                pokemon.pokemonInfo.name
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
            if(pokemon.ability.name == "근성") 1f else if (pokemon.statusAbnormality == StatusAbnormality.BURN && move.type == "물리") 0.5f else 1f
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

        val fruitEffect = getFruitEffect(opponentPokemon.item, move)
        return floor(abilityEffect1 * itemEffect * abilityEffect2 * fruitEffect)
    }

    private fun getFruitEffect(item: ItemInfo, move: MoveInfo) : Float{
        return when(item.name){
            "오카열매" -> if(move.type == "불꽃") 0.5f else 1f
            "꼬시개열매" -> if(move.type == "물") 0.5f else 1f
            "초나열매" -> if(move.type == "전기") 0.5f else 1f
            "린드열매" -> if(move.type == "풀") 0.5f else 1f
            "플카열매" -> if(move.type == "얼음") 0.5f else 1f
            "로플열매" -> if(move.type == "격투") 0.5f else 1f
            "으름열매" -> if(move.type == "독") 0.5f else 1f
            "슈캐열매" -> if(move.type == "땅") 0.5f else 1f
            "바코열매" -> if(move.type == "비행") 0.5f else 1f
            "야파열매" -> if(move.type == "에스퍼") 0.5f else 1f
            "리체열매" -> if(move.type == "벌레") 0.5f else 1f
            "루미열매" -> if(move.type == "바위") 0.5f else 1f
            "수불열매" -> if(move.type == "고스트") 0.5f else 1f
            "하반열매" -> if(move.type == "드래곤") 0.5f else 1f
            "마코열매" -> if(move.type == "악") 0.5f else 1f
            "바리비열매" -> if(move.type == "강철") 0.5f else 1f
            "카리열매" -> if(move.type == "노말") 0.5f else 1f
            "로셀열매" -> if(move.type == "페어리") 0.5f else 1f
            else -> 1f
        }
    }
}