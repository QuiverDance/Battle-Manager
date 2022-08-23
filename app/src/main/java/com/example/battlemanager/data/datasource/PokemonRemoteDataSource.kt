package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.model.PokemonResponse

interface PokemonRemoteDataSource {
    fun getPokemonForName(name: String) : PokemonResponse
    fun getPokemons() : List<PokemonResponse>
    fun getPokemonNameList() : List<String>
}