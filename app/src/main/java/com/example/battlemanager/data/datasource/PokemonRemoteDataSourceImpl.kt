package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.api.PokemonService
import com.example.battlemanager.data.model.PokemonResponse
import com.example.battlemanager.retrofit.RetrofitServiceFactory
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor() : PokemonRemoteDataSource {
    private val service = RetrofitServiceFactory.createService<PokemonService>()

    override suspend fun getPokemonForName(name: String) : PokemonResponse{
        return service.getPokemonForName(name)
    }

    override suspend fun getPokemons(): List<PokemonResponse> {
        return service.getPokemons()
    }

    override suspend fun getPokemonNameList(): List<String> {
        return service.getPokemonNameList()
    }
}