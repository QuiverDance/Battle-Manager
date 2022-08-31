package com.example.battlemanager.presentation.global.util

object FieldUtil {
    private val weatherList = listOf("없음", "햇살이 강함", "햇살이 아주 강함", "비", "강한 비", "모래바람", "싸라기눈")
    fun getWeatherList() : List<String>{
        return weatherList
    }
    private val fieldList = listOf("없음", "일렉트릭필드", "사이코필드", "미스트필드", "그래스필드")
    fun getFieldList() : List<String>{
        return fieldList
    }
}