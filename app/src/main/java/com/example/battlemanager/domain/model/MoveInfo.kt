package com.example.battlemanager.domain.model

data class MoveInfo(
    val id : Int,
    val name : String,
    val power : Int,
    val type : String,
    val category : String,
    val accuracy : Int,
    val description : String,
    val isEffect : Boolean
)