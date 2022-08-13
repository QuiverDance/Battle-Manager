package com.example.battlemanager.domain.model

data class BaseStats (
    val H : Int,
    val A : Int,
    val B : Int,
    val C : Int,
    val D : Int,
    val S : Int,
){
    override fun toString(): String {
        return "H: $H A: $A B: $B C: $C D: $D S: $S"
    }
}