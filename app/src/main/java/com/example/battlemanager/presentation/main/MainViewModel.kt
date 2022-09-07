package com.example.battlemanager.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.battlemanager.domain.model.Pokemon
import com.example.battlemanager.presentation.PokemonMemory
import com.example.battlemanager.presentation.global.base.BaseViewModel
import com.example.battlemanager.presentation.global.constant.Stat
import com.example.battlemanager.presentation.global.util.RealStatUtil
import com.example.battlemanager.presentation.global.util.SingleLiveEvent
import com.example.battlemanager.presentation.global.util.StatUtil

class MainViewModel : BaseViewModel() {

    private val _pokemon1 = MutableLiveData<Pokemon>()
    private val _pokemon2 = MutableLiveData<Pokemon>()
    val pokemon1: LiveData<Pokemon> get() = _pokemon1
    val pokemon2: LiveData<Pokemon> get() = _pokemon2

    fun setPokemon(pokemon: Pokemon, target: Int) {
        pokemon.maxHp = StatUtil.getHp(
            pokemon.pokemonInfo.name,
            pokemon.level,
            pokemon.pokemonInfo.baseStats[Stat.HP],
            pokemon.individualValues.H,
            pokemon.effortValues.H
        )
        if (target == 1) _pokemon1.postValue(pokemon)
        else _pokemon2.postValue(pokemon)
    }

    private val _weather = MutableLiveData<String>("없음")
    private val _field = MutableLiveData<String>("없음")
    val weather: LiveData<String> get() = _weather
    val field: LiveData<String> get() = _field

    fun setWeather(value: String) = _weather.postValue(value)
    fun setField(value: String) = _field.postValue(value)

    private val _preemptivePokemon = MutableLiveData<Int>(0)
    val preemptivePokemon: LiveData<Int> get() = _preemptivePokemon

    fun setPreemptivePokemon() {
        val pokemon1 = PokemonMemory.getPokemon(1)
        val pokemon2 = PokemonMemory.getPokemon(2)

        val speed1 = RealStatUtil.getSpeed(
            pokemon1.level,
            pokemon1.pokemonInfo.baseStats[Stat.SPEED],
            pokemon1.individualValues.S,
            pokemon1.effortValues.S,
            pokemon1.nature,
            pokemon1.rankStates,
            pokemon1.ability,
            pokemon1.item,
            pokemon1.statusAbnormality,
            weather.value!!,
            pokemon1.pokemonInfo.name
        )
        val speed2 = RealStatUtil.getSpeed(
            pokemon2.level,
            pokemon2.pokemonInfo.baseStats[Stat.SPEED],
            pokemon2.individualValues.S,
            pokemon2.effortValues.S,
            pokemon2.nature,
            pokemon2.rankStates,
            pokemon2.ability,
            pokemon2.item,
            pokemon2.statusAbnormality,
            weather.value!!,
            pokemon2.pokemonInfo.name
        )

        if(speed1 > speed2)
            _preemptivePokemon.postValue(1)
        else if(speed2 > speed1)
            _preemptivePokemon.postValue(2)
        else
            _preemptivePokemon.postValue(0)
    }

    val setId = MutableLiveData(-1)
    val startPokemon = SingleLiveEvent<Any>()
    fun startPokemon(pos: Int) {
        setId.value = pos
        startPokemon.call()
    }

    val startResult = SingleLiveEvent<Any>()
    fun onStartResult() {
        startResult.call()
    }

    val startSwap = SingleLiveEvent<Any>()
    fun onStartSwap() {
        startSwap.call()
    }

    val infoId = MutableLiveData(-1)
    val startShowInfo = SingleLiveEvent<Any>()
    fun onShowInfo(pos: Int) {
        infoId.value = pos
        startShowInfo.call()
    }
}