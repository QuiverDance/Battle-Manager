package com.example.battlemanager.data.api

import com.example.battlemanager.data.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon/one")
    fun getPokemonForName(@Query("name") name: String): Call<PokemonResponse>

    @GET("pokemon/all")
    fun getPokemons(): Call<List<PokemonResponse>>

    @GET("pokemon/all/name")
    fun getPokemonNameList(): Call<List<String>>
}