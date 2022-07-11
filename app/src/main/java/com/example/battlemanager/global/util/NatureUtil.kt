package com.example.battlemanager.global.util

import java.lang.Exception

object NatureUtil {
    private val natureTable = listOf(
        //              공     방    특공    특방     스
        listOf<Float>(  1.1f,  0.9f,    1f,    1f,    1f), //외로움을 타는
        listOf<Float>(  1.1f,    1f,  0.9f,    1f,    1f), //고집스런
        listOf<Float>(  1.1f,    1f,    1f,  0.9f,    1f), //개구쟁이같은
        listOf<Float>(  1.1f,    1f,    1f,    1f,  0.9f), //용감한
        listOf<Float>(  0.9f,  1.1f,    1f,    1f,    1f), //대담한
        listOf<Float>(    1f,  1.1f,  0.9f,    1f,    1f), //장난꾸러기같은
        listOf<Float>(    1f,  1.1f,    1f,  0.9f,    1f), //촐랑거리는
        listOf<Float>(    1f,  1.1f,    1f,    1f,  0.9f), //무사태평한
        listOf<Float>(  0.9f,    1f,  1.1f,    1f,    1f), //조심스러운
        listOf<Float>(    1f,  0.9f,  1.1f,    1f,    1f), //의젓한
        listOf<Float>(    1f,    1f,  1.1f,  0.9f,    1f), //덜렁거리는
        listOf<Float>(    1f,    1f,  1.1f,    1f,  0.9f), //냉정한
        listOf<Float>(  0.9f,    1f,    1f,  1.1f,    1f), //차분한
        listOf<Float>(    1f,  0.9f,    1f,  1.1f,    1f), //얌전한
        listOf<Float>(    1f,    1f,  0.9f,  1.1f,    1f), //신중한
        listOf<Float>(    1f,    1f,    1f,  1.1f,  0.9f), //건방진
        listOf<Float>(  0.9f,    1f,    1f,    1f,  1.1f), //겁쟁이같은
        listOf<Float>(    1f,  0.9f,    1f,    1f,  1.1f), //성급한
        listOf<Float>(    1f,    1f,  0.9f,    1f,  1.1f), //명랑한
        listOf<Float>(    1f,    1f,    1f,  0.9f,  1.1f), //천진난만한
        listOf<Float>(    1f,    1f,    1f,    1f,    1f), //수줍음을 타는
        listOf<Float>(    1f,    1f,    1f,    1f,    1f), //노력하는
        listOf<Float>(    1f,    1f,    1f,    1f,    1f), //온순한
        listOf<Float>(    1f,    1f,    1f,    1f,    1f), //변덕스러운
        listOf<Float>(    1f,    1f,    1f,    1f,    1f), //성실한
        listOf<Float>(    1f,    1f,    1f,    1f,    1f), //None
    )
    fun natureToString(nature : Int) : String{
        return when(nature){
            0 -> "외로움을 타는"
            1 -> "고집스런"
            2 -> "개구쟁이같은"
            3 -> "용감한"
            4 -> "대담한"
            5 -> "장난꾸러기같은"
            6 -> "촐랑거리는"
            7 -> "무사태평한"
            8 -> "조심스러운"
            9 -> "의젓한"
            10 -> "덜렁거리는"
            11 -> "냉정한"
            12 -> "차분한"
            13 -> "얌전한"
            14 -> "신중한"
            15 -> "건방진"
            16 -> "겁쟁이같은"
            17 -> "성급한"
            18 -> "명랑한"
            19 -> "천진난만한"
            20 -> "수줍음을 타는"
            21 -> "노력하는"
            22 -> "온순한"
            23 -> "변덕스러운"
            24 -> "성실한"
            25 -> "선택안함"
            else -> throw Exception("It is not exist")
        }
    }
    fun getNatureCorrection(nature : Int) : List<Float>{
        return natureTable[nature]
    }
}