package com.example.battlemanager.global.util

object RankUtil {
    fun getBaseStatRankChange(rank : Int) : Float{
        return when(rank){
            0 -> 1.0f
            1 -> 1.5f
            2 -> 2.0f
            3 -> 2.5f
            4 -> 3.0f
            5 -> 3.5f
            6 -> 4.0f
            -1 -> 0.66f
            -2 -> 0.5f
            -3 -> 0.4f
            -4 -> 0.33f
            -5 -> 0.29f
            -6 -> 0.25f
            else -> 1.0f
        }
    }
    fun getSpecialRankChange(rank : Int) : Float{
        return when(rank){
            0 -> 1.0f
            1 -> 1.33f
            2 -> 1.66f
            3 -> 2.0f
            4 -> 2.33f
            5 -> 2.66f
            6 -> 3.0f
            -1 -> 0.75f
            -2 -> 0.6f
            -3 -> 0.5f
            -4 -> 0.43f
            -5 -> 0.38f
            -6 -> 0.33f
            else -> 1.0f
        }
    }
    fun getCriticalRankChange(rank : Int) : Float{
        return when(rank){
            0 -> 0.0625f
            1 -> 0.125f
            2 -> 0.5f
            3 -> 1.0f
            else -> 0.0625f
        }
    }
}