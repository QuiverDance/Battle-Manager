package com.example.battlemanager.presentation.global.util

object MoveUtil {
    fun isRecoilMove(move: String): Boolean {
        return when (move) {
            "브레이드버드",
            "이판사판태클",
            "플레어드라이브",
            "아프로브레이크",
            "양날박치기",
            "무릎차기",
            "점프킥",
            "파멸의빛",
            "지옥의바퀴",
            "돌진",
            "볼트태클",
            "우드해머",
            "와일드볼트" -> true
            else -> false
        }
    }
    fun isPunchMove(move: String): Boolean{
        return when(move){
            "그로우펀치",
            "냉동펀치",
            "더블펀치",
            "드레인펀치",
            "마하펀치",
            "메가톤펀치",
            "번개펀치",
            "불꽃펀치",
            "불릿펀치",
            "섀도펀치",
            "스카이업퍼",
            "아이스해머",
            "암해머",
            "연속펀치", 
            "잼잼펀치",
            "코멧펀치",
            "폭발펀치",
            "플라스마피스트",
            "힘껏펀치"-> true
            else -> false
        }
    }
}