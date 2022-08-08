package com.example.battlemanager.presentation

import com.example.battlemanager.domain.model.Pokemon

object PokemonMemory {
    private lateinit var firstPokemon : Pokemon
    private lateinit var secondPokemon : Pokemon

    private var isFirstSet = false
    private var isSecondSet = false

    fun getPokemon(pos : Int) : Pokemon{
        return if(pos == 1) firstPokemon else secondPokemon
    }
    fun setPokemon(pos : Int, pokemon: Pokemon){
        if(pos == 1){
            firstPokemon = pokemon
            isFirstSet = true
        }
        else {
            secondPokemon = pokemon
            isSecondSet = true
        }
    }
    fun getIsValid(pos: Int) : Boolean{
        return if(pos == 1) isFirstSet else isSecondSet
    }
}