package com.example.battlemanager.domain.model

data class RankStates(
    val attack : Int,
    val defense : Int,
    val specialAttack : Int,
    val special_defense : Int,
    val speed : Int,
    val accuracy : Int,
    val evasionRate : Int,
    val criticalRate : Int
)