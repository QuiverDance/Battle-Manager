package com.example.battlemanager.domain.repository

import com.example.battlemanager.domain.model.PokemonInfo

interface PokemonRepository {
    suspend fun getPokemonInfoForName(name: String) : PokemonInfo
    suspend fun getPokemonInfoList() : List<PokemonInfo>
    suspend fun getPokemonNameList() : List<String>
}