package com.example.battlemanager.presentation.global.util

import com.example.battlemanager.presentation.global.constant.Gender

object GenderUtil {
    fun genderToString(genderId : Int) : String {
        return when(genderId){
            -1 -> "미설정"
            0 -> "무성"
            1 -> "남"
            2 -> "여"
            else -> "genderId error"
        }
    }
    fun getGenderList(genderId : Int) : List<String>{
        return when(genderId){
            0 -> listOf("무성")
            1 -> listOf("남")
            2 -> listOf("여")
            3 -> listOf("남", "여")
            else -> listOf("genderId error")
        }

    }
    fun getGenderId(str: String) : Int{
        return when(str){
            "무성" -> Gender.NONE
            "남" -> Gender.MALE
            "여" -> Gender.FEMALE
            else -> Gender.NULL
        }
    }
}