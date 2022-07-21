package com.example.battlemanager.domain.model

data class PokemonInfo(
    val id : Int,
    val dexId : Int,
    val name : String,
    val imageUrl : String,
    val typeList : List<String>,
    val validAbilityList : List<String>,
    val validGender : Int,
    val weight : Float,
    val baseStats: BaseStats
)