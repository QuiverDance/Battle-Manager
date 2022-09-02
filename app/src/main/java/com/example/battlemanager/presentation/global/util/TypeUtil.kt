package com.example.battlemanager.presentation.global.util

import com.example.battlemanager.R

object TypeUtil {
    private val typeList = listOf("노말", "불꽃", "물", "전기", "풀", "얼음", "격투", "독", "땅", "비행", "에스퍼", "벌레", "바위", "고스트", "드래곤", "악", "강철", "페어리")
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
        return typeList[type]
    }
    fun typeToInt(type : String) : Int{
        for((idx, item) in typeList.withIndex()){
            if(item == type)
                return idx
        }
        return -1
    }
    fun getTypeCorrelation(type1 : Int, type2 : Int) : Float{
        return typeTable[type1][type2]
    }
    fun getTypeCorrelation(type1 : String, type2 : String) : Float{
        return if(type1 == "" || type2 == "") 1f
        else typeTable[typeToInt(type1)][typeToInt(type2)]
    }
    fun getTypeBackground(type : String) : Int{
        return when(type){
            "노말" -> R.drawable.background_normal
            "불꽃" -> R.drawable.background_fire
            "물" -> R.drawable.background_water
            "풀" -> R.drawable.background_grass
            "전기" -> R.drawable.background_electric
            "얼음" -> R.drawable.background_ice
            "격투" -> R.drawable.background_fighting
            "독" -> R.drawable.background_poison
            "땅" -> R.drawable.background_ground
            "비행" -> R.drawable.background_flying
            "에스퍼" -> R.drawable.background_psychic
            "벌레" -> R.drawable.background_bug
            "바위" -> R.drawable.background_rock
            "고스트" -> R.drawable.background_ghost
            "드래곤" -> R.drawable.background_dragon
            "악" -> R.drawable.background_dark
            "강철" -> R.drawable.background_steel
            "페어리" -> R.drawable.background_fairy
            else -> R.drawable.background_radius_5
        }
    }
    fun getTypeList() : List<String>{
        return typeList
    }
}