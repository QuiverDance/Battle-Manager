package com.example.battlemanager.domain.repository

import com.example.battlemanager.domain.model.PokemonInfo

interface PokemonRepository {
    fun getPokemonInfoForName(name: String) : PokemonInfo
    fun getPokemonInfoList() : List<PokemonInfo>
    fun getPokemonNameList() : List<String>
}