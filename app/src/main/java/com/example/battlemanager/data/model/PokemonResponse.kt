package com.example.battlemanager.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("dexId") val dexId : Int,
    @SerializedName("name")val name : String,
    @SerializedName("imageUrl")val imageUrl : String,
    @SerializedName("typeList")val typeList : List<Int>,
    @SerializedName("validAbilityList")val validAbilityList : List<String>,
    @SerializedName("validGender")val validGender : Int,
    @SerializedName("weight")val weight : Float,
    @SerializedName("baseStats")val baseStats: BaseStats
)