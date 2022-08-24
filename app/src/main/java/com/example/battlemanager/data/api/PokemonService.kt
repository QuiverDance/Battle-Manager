package com.example.battlemanager.data.api

import com.example.battlemanager.data.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemons/one")
    fun getPokemonForName(@Query("name") name: String): Call<PokemonResponse>

    @GET("pokemons/all")
    fun getPokemons(): Call<List<PokemonResponse>>

    @GET("pokemons/all/name")
    fun getPokemonNameList(): Call<List<String>>
}