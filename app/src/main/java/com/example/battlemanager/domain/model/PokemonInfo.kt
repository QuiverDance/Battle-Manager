package com.example.battlemanager.domain.model

data class PokemonInfo(
    val id : Long,
    val dexId : Int,
    val name : String,
    val imageUrl : String,
    val type1 : String,
    val type2: String,
    val validAbilityList : List<String>,
    val validGender : Int,
    val weight : Double,
    val baseStats: List<Int>
){
    override fun toString(): String {
        return "도감번호: $dexId 이름: $name 타입: $type1 $type2 종족값: $baseStats"
    }
}