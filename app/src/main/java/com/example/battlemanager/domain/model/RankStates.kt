package com.example.battlemanager.domain.model

data class RankStates(
    val attack : Int,
    val defense : Int,
    val specialAttack : Int,
    val specialDefense : Int,
    val speed : Int,
    val accuracy : Int,
    val evasionRate : Int,
    val criticalRate : Int
){
    override fun toString(): String {
        return "A : $attack B: $defense C: $specialAttack D: $specialDefense S: $speed ACC: $speed EVA: $evasionRate CRI: $criticalRate"
    }
}