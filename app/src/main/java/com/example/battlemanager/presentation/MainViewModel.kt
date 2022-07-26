package com.example.battlemanager.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.domain.model.Pokemon
import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.usecase.pokemon.GetPokemonInfoUseCase
import com.example.battlemanager.domain.usecase.pokemon.GetPokemonNameListUseCase
import com.example.battlemanager.global.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _pokemon1 = MutableLiveData<Pokemon>(Pokemon())
    private val _pokemon2 = MutableLiveData<Pokemon>(Pokemon())
    val pokemon1: LiveData<Pokemon> get() =_pokemon1
    val pokemon2: LiveData<Pokemon> get() =_pokemon2

    fun setPokemon(pokemon: Pokemon, target: Int){
        if(target == 1) _pokemon1.postValue(pokemon)
        else _pokemon2.postValue(pokemon)
    }

    val startPokemon1 = SingleLiveEvent<Any>()
    fun startPokemon1() {
        startPokemon1.call()
    }
    val startPokemon2 = SingleLiveEvent<Any>()
    fun startPokemon2() {
        startPokemon2.call()
    }
}