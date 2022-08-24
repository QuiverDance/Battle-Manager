package com.example.battlemanager.presentation.global.util

object NatureUtil {
    private val natureList = listOf("선택안함", "외로움을 타는", "고집스런", "개구쟁이같은", "용감한", "대담한", "장난꾸러기같은", "촐랑거리는", "무사태평한", "조심스러운", "의젓한", "덜렁거리는",
        "냉정한", "차분한", "얌전한", "신중한", "건방진", "겁쟁이같은", "성급한", "명랑한", "천진난만한", "수줍음을 타는", "노력하는", "온순한", "변덕스러운", "성실한")
    private val natureTable = listOf(
        //              공     방    특공    특방     스
        listOf<Float>(    1f,    1f,    1f,    1f,    1f), //None
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
    )
    fun natureToString(nature : Int) : String{
        return natureList[nature]
    }
    fun getNatureCorrection(nature : Int) : List<Float>{
        return natureTable[nature]
    }
    fun getNatureList() : List<String>{
        return natureList
    }
    fun getNatureId(str: String) : Int{
        var idx = 0
        for(value in natureList){
            if(value == str)
                break
            idx += 1
        }
        return idx
    }
}