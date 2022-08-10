package com.example.battlemanager.global.util

import kotlin.math.*

object StatUtil {
    fun getHp(name : String, level : Int, baseH: Int, ivH: Int, evH : Int) :Int{
        if(name == "껍질몬") return 1
        return floor(((baseH * 2) + ivH + floor(evH*0.25)) * (level * 0.01)).toInt() +10 + level
    }
    fun getA(level: Int, baseA: Int, ivA: Int, evA: Int, nature: Int) : Int{
        return floor((floor(((baseA * 2) + ivA + floor(evA * 0.25)) * (level * 0.01)).toInt() + 5) * NatureUtil.getNatureCorrection(nature)[0]).toInt()
    }
    fun getB(level: Int, baseB: Int, ivB: Int, evB: Int, nature: Int) : Int{
        return floor((floor(((baseB * 2) + ivB + floor(evB * 0.25)) * (level * 0.01)).toInt() + 5) * NatureUtil.getNatureCorrection(nature)[1]).toInt()
    }
    fun getC(level: Int, baseC: Int, ivC: Int, evC: Int, nature: Int) : Int{
        return floor((floor(((baseC * 2) + ivC + floor(evC * 0.25)) * (level * 0.01)).toInt() + 5) * NatureUtil.getNatureCorrection(nature)[2]).toInt()
    }
    fun getD(level: Int, baseD: Int, ivD: Int, evD: Int, nature: Int) : Int{
        return floor((floor(((baseD * 2) + ivD + floor(evD * 0.25)) * (level * 0.01)).toInt() + 5) * NatureUtil.getNatureCorrection(nature)[3]).toInt()
    }
    fun getS(level: Int, baseS: Int, ivS: Int, evS: Int, nature: Int) : Int{
        return floor((floor(((baseS * 2) + ivS + floor(evS * 0.25)) * (level * 0.01)).toInt() + 5) * NatureUtil.getNatureCorrection(nature)[4]).toInt()
    }
}