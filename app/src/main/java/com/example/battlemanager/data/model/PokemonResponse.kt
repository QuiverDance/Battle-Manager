package com.example.battlemanager.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("dexId") val dexId : Int,
    @SerializedName("name")val name : String,
    @SerializedName("imgUrl")val imgUrl : String,
    @SerializedName("type1")val type1 : String,
    @SerializedName("type2")val type2 : String,
    @SerializedName("ability1")val ability1 : String,
    @SerializedName("ability2")val ability2 : String,
    @SerializedName("ability3")val ability3 : String,
    @SerializedName("genderType")val genderType : Int,
    @SerializedName("weight")val weight : Float,
    @SerializedName("h")val h: Int,
    @SerializedName("a")val a: Int,
    @SerializedName("b")val b: Int,
    @SerializedName("c")val c: Int,
    @SerializedName("d")val d: Int,
    @SerializedName("s")val s: Int
)