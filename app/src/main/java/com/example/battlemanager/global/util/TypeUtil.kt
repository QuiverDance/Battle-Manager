package com.example.battlemanager.global.util

import java.lang.Exception

object TypeUtil {
    private val typeTable = listOf(
        //      노     불    물    전     풀    얼    격    독     땅    비     에     벌     바    고    드    악     강     페
        listOf<Float>(    1f,    1f,    1f,    1f,    1f,    1f,    1f,    1f,    1f,    1f,    1f,     1f,  0.5f,    0f,    1f,    1f,  0.5f,    1f), //노말
        listOf<Float>(    1f,  0.5f,  0.5f,    1f,    2f,    2f,    1f,    1f,    1f,    1f,    1f,     2f,  0.5f,    1f,  0.5f,    1f,    2f,    1f), //불꽃
        listOf<Float>(    1f,    2f,  0.5f,    1f,  0.5f,    1f,    1f,    1f,    2f,    1f,    1f,     1f,    2f,    1f,  0.5f,    1f,  0.5f,    1f), //물
        listOf<Float>(    1f,    1f,    2f,  0.5f,  0.5f,    1f,    1f,    1f,    0f,    2f,    1f,     1f,    1f,    1f,  0.5f,    1f,  0.5f,    1f), //전기
        listOf<Float>(    1f,  0.5f,    2f,    1f,  0.5f,    1f,    1f,  0.5f,    2f,  0.5f,    1f,   0.5f,    2f,    1f,  0.5f,    1f,  0.5f,    1f), //풀
        listOf<Float>(    1f,  0.5f,  0.5f,    1f,    2f,  0.5f,    1f,    1f,    2f,    2f,    1f,     1f,    1f,    1f,    2f,    1f,  0.5f,    1f), //얼음
        listOf<Float>(    2f,    1f,    1f,    1f,    1f,    2f,    1f,  0.5f,    1f,  0.5f,  0.5f,   0.5f,    2f,    0f,    1f,    2f,    2f,  0.5f), //격투
        listOf<Float>(    1f,    1f,    1f,    1f,    2f,    1f,    1f,  0.5f,  0.5f,    1f,    1f,     1f,  0.5f,  0.5f,    1f,    1f,    0f,    2f), //독
        listOf<Float>(    1f,    2f,    1f,    2f,  0.5f,    1f,    1f,    2f,    1f,    0f,    1f,   0.5f,    2f,    1f,    1f,    1f,    2f,    1f), //땅
        listOf<Float>(    1f,    1f,    1f,  0.5f,    2f,    1f,    2f,    1f,    1f,    1f,    1f,     2f,  0.5f,    1f,    1f,    1f,  0.5f,    1f), //비행
        listOf<Float>(    1f,    1f,    1f,    1f,    1f,    1f,    2f,    2f,    1f,    1f,  0.5f,     1f,    1f,    1f,    1f,    0f,  0.5f,    1f), //에스퍼
        listOf<Float>(    1f,  0.5f,    1f,    1f,    2f,    1f,  0.5f,  0.5f,    1f,  0.5f,    2f,     1f,    1f,  0.5f,    1f,    2f,  0.5f,  0.5f), //벌레
        listOf<Float>(    1f,    2f,    1f,    1f,    1f,    2f,  0.5f,    1f,  0.5f,    2f,    1f,     2f,    1f,    1f,    1f,    1f,  0.5f,    1f), //바위
        listOf<Float>(    0f,    1f,    1f,    1f,    1f,    1f,    1f,    1f,    1f,    1f,    2f,     1f,    1f,    2f,    1f,  0.5f,    1f,    1f), //고스트
        listOf<Float>(    1f,    1f,    1f,    1f,    1f,    1f,    1f,    1f,    1f,    1f,    1f,     1f,    1f,    1f,    2f,    1f,  0.5f,    0f), //드래곤
        listOf<Float>(    1f,    1f,    1f,    1f,    1f,    1f,  0.5f,    1f,    1f,    1f,    2f,     1f,    1f,    2f,    1f,  0.5f,    1f,  0.5f), //악
        listOf<Float>(    1f,  0.5f,  0.5f,  0.5f,    1f,    2f,    1f,    1f,    1f,    1f,    1f,     1f,    2f,    1f,    1f,    1f,  0.5f,    2f), //강철
        listOf<Float>(    1f,  0.5f,    1f,    1f,    1f,    1f,    2f,  0.5f,    1f,    1f,    1f,     1f,    1f,    1f,    2f,    2f,  0.5f,    1f), //페어리
    )
    fun typeToString(type : Int) : String{
        return when(type){
            0 -> "노말"
            1 -> "불꽃"
            2 -> "물"
            3 -> "전기"
            4 -> "풀"
            5 -> "얼음"
            6 -> "격투"
            7 -> "독"
            8 -> "땅"
            9 -> "비행"
            10 -> "에스퍼"
            11 -> "벌레"
            12 -> "바위"
            13 -> "고스트"
            14 -> "드래곤"
            15 -> "악"
            16 -> "강철"
            17 -> "페어리"
            else -> throw Exception("It is not exist")
        }
    }
    fun getTypeCorrelation(type1 : Int, type2 : Int) : Float{
        return typeTable[type1][type2]
    }
}