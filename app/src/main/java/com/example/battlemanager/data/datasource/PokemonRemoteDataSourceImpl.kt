package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.api.PokemonService
import com.example.battlemanager.data.model.PokemonResponse

class PokemonRemoteDataSourceImpl(private val service : PokemonService) : PokemonRemoteDataSource {
    override suspend fun getPokemon(id : Int) : PokemonResponse{
        return service.getPokemon(id)
    }

    override suspend fun getPokemons(): List<PokemonResponse> {
        return service.getPokemons()
    }

    override suspend fun getPokemonsName(): List<String> {
        return service.getPokemonsName()
    }
}