package com.example.battlemanager.presentation.global.util

import com.example.battlemanager.domain.model.ItemInfo
import com.example.battlemanager.domain.model.MoveInfo

object ItemUtil {
    /*
    진화의 휘석 미적용
     */
    
    fun getAttackCorrelation(itemInfo: ItemInfo, pokemonName: String): Float {
        return when (itemInfo.name) {
            "구애머리띠" -> 1.5f
            "굵은뼈" -> if (pokemonName == "탕구리" || pokemonName == "텅구리") 2f else 1f
            "전기구슬" -> if(pokemonName == "피카츄") 2f else 1f
            else -> 1.0f
        }
    }

    fun getSpecialAttackCorrelation(itemInfo: ItemInfo, pokemonName: String): Float {
        return when (itemInfo.name) {
            "구애안경" -> 1.5f
            "심해의이빨" -> if(pokemonName == "진주몽") 2f else 1f
            "전기구슬" -> if(pokemonName == "피카츄") 2f else 1f
            else -> 1.0f
        }
    }

    fun getDefenseCorrelation(itemInfo: ItemInfo, pokemonName: String): Float {
        return when (itemInfo.name) {
            "금속파우더" -> if (pokemonName == "메타몽") 1.5f else 1f
            else -> 1f
        }
    }

    fun getSpecialDefenseCorrelation(itemInfo: ItemInfo, pokemonName: String): Float {
        return when (itemInfo.name) {
            "금속파우더" -> if (pokemonName == "메타몽") 1.5f else 1f
            "심해의비늘" -> if(pokemonName == "진주몽") 2f else 1f
            "돌격조끼" -> 1.5f
            else -> 1f
        }
    }

    fun getSpeedCorrelation(itemInfo: ItemInfo, pokemonName: String): Float {
        return when (itemInfo.name) {
            "구애스카프" -> 1.5f
            "스피드파우더" -> if (pokemonName == "메타몽") 1.5f else 1f
            else -> 1f
        }
    }

    fun getPowerCorrelation(itemInfo: ItemInfo, move: MoveInfo, pokemonName: String): Float {
        return when (itemInfo.name) {
            "박식안경" -> if (move.category == "특수") 1.1f else 1f
            "힘의머리띠" -> if (move.category == "물리") 1.1f else 1f
            "강철플레이트" -> if (move.type == "강철") 1.2f else 1f
            "고드름플레이트" -> if (move.type == "얼음") 1.2f else 1f
            "공포플레이트" -> if (move.type == "악") 1.2f else 1f
            "대지플레이트" -> if (move.type == "땅") 1.2f else 1f
            "맹독플레이트" -> if (move.type == "독") 1.2f else 1f
            "물방울플레이트" -> if (move.type == "물") 1.2f else 1f
            "불구슬플레이트" -> if (move.type == "불") 1.2f else 1f
            "비단벌레플레이트" -> if (move.type == "벌레") 1.2f else 1f
            "암석플레이트" -> if (move.type == "바위") 1.2f else 1f
            "용의플레이트" -> if (move.type == "드래곤") 1.2f else 1f
            "우뢰플레이트" -> if (move.type == "전기") 1.2f else 1f
            "원령플레이트" -> if (move.type == "고스트") 1.2f else 1f
            "이상한플레이트" -> if (move.type == "에스퍼") 1.2f else 1f
            "주먹플레이트" -> if (move.type == "격투") 1.2f else 1f
            "초록플레이트" -> if (move.type == "풀") 1.2f else 1f
            "푸른하늘플레이트" -> if (move.type == "비행") 1.2f else 1f
            "정령플레이트" -> if (move.type == "페어리") 1.2f else 1f
            "마음의물방울" -> if ((pokemonName == "라티아스" || pokemonName == "라티오스") && (move.type == "에스퍼" || move.type == "드래곤")) 1.2f else 1f
            "검은띠" -> if(move.type == "격투") 1.2f else 1f
            "검은안경" -> if(move.type == "악") 1.2f else 1f
            "금속코트" -> if(move.type == "강철") 1.2f else 1f
            "기적의씨" -> if(move.type == "풀") 1.2f else 1f
            "녹지않는얼음" -> if(move.type == "얼음") 1.2f else 1f
            "독바늘" -> if(move.type == "독") 1.2f else 1f
            "딱딱한돌" -> if(move.type == "바위") 1.2f else 1f
            "목탄" -> if(move.type == "불꽃") 1.2f else 1f
            "부드러운모래" -> if(move.type == "땅") 1.2f else 1f
            "신비의물방울" -> if(move.type == "물") 1.2f else 1f
            "실크스카프" -> if(move.type == "노말") 1.2f else 1f
            "예리한부리" -> if(move.type == "비행") 1.2f else 1f
            "용의이빨" -> if(move.type == "드래곤") 1.2f else 1f
            "은빛가루" -> if(move.type == "벌레") 1.2f else 1f
            "자석" -> if(move.type == "전기") 1.2f else 1f
            "저주의부적" -> if(move.type == "고스트") 1.2f else 1f
            "휘어진스푼" -> if(move.type == "에스퍼") 1.2f else 1f
            "금강옥" -> if(pokemonName == "디아루가" && (move.type == "강철" || move.type == "드래곤")) 1.2f else 1f
            "백옥" -> if(pokemonName == "펄기아" && (move.type == "물" || move.type == "드래곤")) 1.2f else 1f
            "백금옥" -> if(pokemonName == "기라티나" && (move.type == "고스트" || move.type == "드래곤")) 1.2f else 1f
            else -> 1f
        }
    }

    private val categoryList =
        listOf("나무열매", "배틀전용도구", "능력치상승도구", "기술강화도구", "플레이트", "주얼", "메가스톤", "원시회귀", "폼체인지")

    fun getItemCategory(): List<String> {
        return listOf("전체") + categoryList
    }
}