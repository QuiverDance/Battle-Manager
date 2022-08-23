package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.api.PokemonService
import com.example.battlemanager.data.model.PokemonResponse
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(private val service : PokemonService) : PokemonRemoteDataSource {

    override fun getPokemonForName(name: String) : PokemonResponse{
        return service.getPokemonForName(name).execute().body()!!
    }

    override fun getPokemons(): List<PokemonResponse> {
        return service.getPokemons().execute().body()!!
    }

    override fun getPokemonNameList(): List<String> {
        return service.getPokemonNameList().execute().body()!!
    }
}