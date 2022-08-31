package com.example.battlemanager.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.battlemanager.domain.model.Pokemon
import com.example.battlemanager.presentation.global.util.SingleLiveEvent
import com.example.battlemanager.presentation.global.util.StatUtil

class MainViewModel : ViewModel() {

    private val _pokemon1 = MutableLiveData<Pokemon>()
    private val _pokemon2 = MutableLiveData<Pokemon>()
    val pokemon1: LiveData<Pokemon> get() = _pokemon1
    val pokemon2: LiveData<Pokemon> get() = _pokemon2

    fun setPokemon(pokemon: Pokemon, target: Int) {
        pokemon.maxHp = StatUtil.getHp(
            pokemon.pokemonInfo.name,
            pokemon.level,
            pokemon.pokemonInfo.baseStats.H,
            pokemon.individualValues.H,
            pokemon.effortValues.H
        )
        if (target == 1) _pokemon1.postValue(pokemon)
        else _pokemon2.postValue(pokemon)
    }

    private val _weather = MutableLiveData<String>("없음")
    private val _field = MutableLiveData<String>("없음")
    fun setWeather(weather: String) = _weather.postValue(weather)
    fun setField(field: String) = _field.postValue(field)

    val startPokemon1 = SingleLiveEvent<Any>()
    fun startPokemon1() {
        startPokemon1.call()
    }

    val startPokemon2 = SingleLiveEvent<Any>()
    fun startPokemon2() {
        startPokemon2.call()
    }

    val startResult = SingleLiveEvent<Any>()
    fun onStartResult() {
        startResult.call()
    }

    val startSwap = SingleLiveEvent<Any>()
    fun onStartSwap() {
        startSwap.call()
    }
}