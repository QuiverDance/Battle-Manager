package com.example.battlemanager.data.model

import com.google.gson.annotations.SerializedName
data class MoveResponse(
    @SerializedName("id")val id : Int,
    @SerializedName("name")val name : String,
    @SerializedName("type")val type : String,
    @SerializedName("category")val category : String,
    @SerializedName("power")val power : Int,
    @SerializedName("accuracy")val accuracy : Int
)