package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.model.PokemonResponse

interface PokemonRemoteDataSource {
    suspend fun getPokemonForName(name: String) : PokemonResponse
    suspend fun getPokemons() : List<PokemonResponse>
    suspend fun getPokemonNameList() : List<String>
}