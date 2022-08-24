package com.example.battlemanager.domain.model

data class MoveInfo(
    val id : Long,
    val name : String,
    val power : Int,
    val type : String,
    val category : String,
    val accuracy : Int,
)