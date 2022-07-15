package com.example.battlemanager.data.api

import com.example.battlemanager.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("/pokemon")
    suspend fun getPokemon(@Query("dexId") dexId : Int): PokemonResponse

    @GET("/pokemons")
    suspend fun getPokemons(): List<PokemonResponse>

    @GET("/pokemons/name")
    suspend fun getPokemonsName(): List<String>
}