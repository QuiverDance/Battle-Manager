package com.example.battlemanager.presentation.global.util

import com.example.battlemanager.domain.model.AbilityInfo
import com.example.battlemanager.domain.model.MoveInfo
import com.example.battlemanager.domain.model.Pokemon
import com.example.battlemanager.presentation.global.constant.StatusAbnormality
import kotlin.math.floor

object AbilityUtil {
    /*
    천사장사 특성이 기술이나 특성에 의해 변경된 경우에는 1.5배
    플라워기프트 미반영
     */
    fun getAttackCorrelation(ability: AbilityInfo, statusAbnormality: Int) : Float{
        return when(ability.name){
            "순수한힘" -> 2f
            "천하장사" -> 2f
            "근성" -> if(statusAbnormality != StatusAbnormality.NONE) 1.5f else 1f
            "의욕" -> 1.5f
            else -> 1.0f
        }
    }
    /*
    플러스, 마이너스 효과 미반영
     */
    fun getSpecialAttackCorrelation(ability: AbilityInfo, weather: String) : Float{
        return when(ability.name){
            "선파워" -> if(weather == "햇살이 강함" || weather == "햇살이 아주 강함") 1.5f else 1f
            else -> 1.0f
        }
    }
    fun getDefenseCorrelation(abilityInfo: AbilityInfo, statusAbnormality: Int) : Float{
        return when(abilityInfo.name){
            "이상한비늘" -> if(statusAbnormality != StatusAbnormality.NONE) 1.5f else 1f
            else -> 1f
        }
    }
    fun getSpecialDefenseCorrelation(abilityInfo: AbilityInfo) : Float{
        return when(abilityInfo.name){
            else -> 1f
        }
    }
    fun getSpeedCorrelation(abilityInfo: AbilityInfo, statusAbnormality: Int, weather: String) : Float{
        return when(abilityInfo.name){
            "속보" -> if(statusAbnormality != StatusAbnormality.NONE) 1.5f else 1f
            "쓱쓱" -> if(weather == "비" || weather == "강한 비") 2f else 1f
            "엽록소" -> if(weather == "햇살이 강함" || weather == "햇살이 아주 강함") 2f else 1f
            else -> 1f
        }
    }
    fun getMyPowerCorrelation(move: MoveInfo, myPokemon: Pokemon, opponentPokemon: Pokemon) : Float{
        return when(myPokemon.ability.name){
            "투쟁심" -> if(opponentPokemon.gender == "무성") 1f else if(myPokemon.gender == opponentPokemon.gender) 1.25f else 0.75f
            "이판사판" -> if(MoveUtil.isRecoilMove(move.name)) 1.2f else 1f
            "철주먹" -> if(MoveUtil.isPunchMove(move.name)) 1.2f else 1f
            "맹화" -> if(move.type == "불꽃" && myPokemon.hp <= floor(myPokemon.maxHp/3f).toInt()) 1.5f else 1f
            "심록" -> if(move.type == "풀" && myPokemon.hp <= floor(myPokemon.maxHp/3f).toInt()) 1.5f else 1f
            "급류" -> if(move.type == "물" && myPokemon.hp <= floor(myPokemon.maxHp/3f).toInt()) 1.5f else 1f
            "벌레의알림" -> if(move.type == "벌레" && myPokemon.hp <= floor(myPokemon.maxHp/3f).toInt()) 1.5f else 1f
            "테크니션" -> if(move.power <= 60) 1.5f else 1f
            else -> 1f
        }
    }
    /*
    건조피부 물타입 피격시 hp 회복 미반영
     */
    fun getOpponentPowerCorrelation(move: MoveInfo, opponentPokemon: Pokemon) : Float{
        return when(opponentPokemon.ability.name){
            "두꺼운지방" -> if(move.type == "불꽃" || move.type == "얼음") 0.5f else 1f
            "내열" -> if(move.type == "불꽃") 0.5f else 1f
            "건조피부" -> if(move.type == "물") 0f else if(move.type == "불꽃") 1.25f else 1f
            "타오르는불꽃" -> if(move.type == "불꽃" && opponentPokemon.statusAbnormality != StatusAbnormality.ICE) 0f else 1f
            else -> 1f
        }
    }
}