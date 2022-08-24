package com.example.battlemanager.presentation.global.util

import com.example.battlemanager.presentation.global.constant.StatusAbnormality

object StatusAbnormalityUtil {
    fun StatusAbnormalityToString(id : Int) : String {
        return when(id){
            -1 -> "없음"
            0 -> "독"
            1 -> "얼음"
            2 -> "마비"
            3 -> "맹독"
            4 -> "잠듦"
            5 -> "화상"
            else -> "Status Abnormality id error"
        }
    }
    fun getStatusAbnormalityList() : List<String>{
        return listOf("없음", "독", "얼음", "마비", "맹독", "잠듦", "화상")
    }
    fun getStatusAbnormalityId(str: String) : Int{
        return when(str){
            "독" -> StatusAbnormality.POISON
            "얼음" -> StatusAbnormality.ICE
            "마비" -> StatusAbnormality.PARALYSIS
            "맹독" -> StatusAbnormality.VENOM
            "잠듦" -> StatusAbnormality.SLEEP
            "화상" -> StatusAbnormality.BURN
            else -> StatusAbnormality.NONE
        }
    }
}