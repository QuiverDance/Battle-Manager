package com.example.battlemanager.data.model

data class PokemonInfo(
    val pokeDexId : Int,
    val name : String,
    val imageUrl : String,
    val typeList : List<Int>,
    val validAbilityList : List<Int>,
    val validGenderList : List<String>,
    val weight : Float
)