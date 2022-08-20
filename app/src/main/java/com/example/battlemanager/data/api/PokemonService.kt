package com.example.battlemanager.data.api

import com.example.battlemanager.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("/pokemon/one")
    suspend fun getPokemonForName(@Query("name") name: String): PokemonResponse

    @GET("/pokemon/all")
    suspend fun getPokemons(): List<PokemonResponse>

    @GET("/pokemon/all/name")
    suspend fun getPokemonNameList(): List<String>
}