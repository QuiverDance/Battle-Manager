package com.example.battlemanager.domain.model

data class PokemonInfo(
    val dexId : Int,
    val name : String,
    val imageUrl : String,
    val typeList : List<Int>,
    val validAbilityList : List<String>,
    val validGender : Int,
    val weight : Float,
    val baseStats: BaseStats
)