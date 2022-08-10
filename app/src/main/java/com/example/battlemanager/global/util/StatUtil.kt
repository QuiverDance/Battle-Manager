package com.example.battlemanager.global.util

import com.example.battlemanager.domain.model.BaseStats
import com.example.battlemanager.domain.model.EffortValues
import com.example.battlemanager.domain.model.IndividualValues
import kotlin.math.*

object StatUtil {
    fun getHp(name : String, level : Int, baseH: Int, ivH: Int, evH : Int) :Int{
        if(name == "껍질몬") return 1
        return floor(((baseH * 2) + ivH + floor(evH*0.25)) * (level * 0.01)).toInt() +10 + level
    }
}