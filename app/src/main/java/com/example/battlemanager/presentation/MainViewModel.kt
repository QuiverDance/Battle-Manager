package com.example.battlemanager.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.usecase.pokemon.GetPokemonInfoUseCase
import com.example.battlemanager.domain.usecase.pokemon.GetPokemonNameListUseCase
import com.example.battlemanager.global.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase = GetPokemonInfoUseCase(),
    private val getPokemonNameListUseCase: GetPokemonNameListUseCase = GetPokemonNameListUseCase()
) : ViewModel() {

    private val _pokemon1 = MutableLiveData<PokemonInfo>(null)
    val pokemon1: LiveData<PokemonInfo> get() =_pokemon1
    fun getPokemon1(id : Int){
        viewModelScope.launch {
            val pokemon = withContext(Dispatchers.IO){
                getPokemonInfoUseCase.invoke(id)
            }
            _pokemon1.value = pokemon
        }
    }
    private val _pokemon2 = MutableLiveData<PokemonInfo>(null)
    val pokemon2: LiveData<PokemonInfo> get() =_pokemon2
    fun getPokemon2(id : Int){
        viewModelScope.launch {
            val pokemon = withContext(Dispatchers.IO){
                getPokemonInfoUseCase.invoke(id)
            }
            _pokemon2.value = pokemon
        }
    }

    private val _pokemonNameList = MutableLiveData<List<FilterItem>>(null)
    val pokemonNameList : LiveData<List<FilterItem>> get() = _pokemonNameList

    fun getPokemonNameList(){
        viewModelScope.launch {
            val nameList = withContext(Dispatchers.IO){
                getPokemonNameListUseCase.invoke()
            }
            _pokemonNameList.value = nameList
        }
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