package com.example.battlemanager.data.model

import com.google.gson.annotations.SerializedName
data class ItemResponse(
    @SerializedName("id")val id : Long,
    @SerializedName("name")val name : String,
    @SerializedName("category")val category : String
)