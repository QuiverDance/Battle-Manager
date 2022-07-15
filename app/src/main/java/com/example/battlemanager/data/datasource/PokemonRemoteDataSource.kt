package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.model.PokemonResponse

interface PokemonRemoteDataSource {
    suspend fun getPokemon(id : Int) : PokemonResponse
    suspend fun getPokemons() : List<PokemonResponse>
    suspend fun getPokemonsName() : List<String>
}