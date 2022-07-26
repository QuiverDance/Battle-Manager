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
import com.example.battlemanager.global.util.GenderUtil
import com.example.battlemanager.global.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonSettingViewModel(
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase = GetPokemonInfoUseCase(),
    private val getPokemonNameListUseCase: GetPokemonNameListUseCase = GetPokemonNameListUseCase()
) : ViewModel() {

    private val _pokemonInfo = MutableLiveData<PokemonInfo>()
    val pokemonInfo: LiveData<PokemonInfo> get() = _pokemonInfo
    fun getPokemonInfo(id : Int, target : Int){
        viewModelScope.launch {
            val pokemonInfo = withContext(Dispatchers.IO){
                getPokemonInfoUseCase.invoke(id)
            }
            _pokemonInfo.value = pokemonInfo
            startSetSpinner.call()
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

    private val _gender = MutableLiveData<String>("성별")
    val gender: LiveData<String> get() =_gender
    fun setGender(id : Int){
        _gender.postValue(GenderUtil.genderToString(id))
    }

    val startSelectPokemon = SingleLiveEvent<Any>()
    fun onSelectPokemon() {
        startSelectPokemon.call()
    }
    val startSetSpinner = SingleLiveEvent<Any>()
    fun onSetSpinner() {
        startSetSpinner.call()
    }
}