package com.example.battlemanager.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.battlemanager.domain.model.Ability
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

    val level = MutableLiveData<String>("50")
    val hp = MutableLiveData<Int>(0)
    val ivH = MutableLiveData<String>("0")
    val ivA = MutableLiveData<String>("0")
    val ivB = MutableLiveData<String>("0")
    val ivC = MutableLiveData<String>("0")
    val ivD = MutableLiveData<String>("0")
    val ivS = MutableLiveData<String>("0")

    val evH = MutableLiveData<String>("0")
    val evA = MutableLiveData<String>("0")
    val evB = MutableLiveData<String>("0")
    val evC = MutableLiveData<String>("0")
    val evD = MutableLiveData<String>("0")
    val evS = MutableLiveData<String>("0")

    val rankA = MutableLiveData<String>("0")
    val rankB = MutableLiveData<String>("0")
    val rankC = MutableLiveData<String>("0")
    val rankD = MutableLiveData<String>("0")
    val rankS = MutableLiveData<String>("0")
    val rankAcc = MutableLiveData<String>("0")
    val rankEva = MutableLiveData<String>("0")
    val rankCri = MutableLiveData<String>("0")

    private val _gender = MutableLiveData<String>()
    val gender: LiveData<String> get() =_gender
    fun setGender(id : Int) = _gender.postValue(GenderUtil.genderToString(id))

    private val _ability = MutableLiveData<String>()
    val ability: LiveData<String> get() =_ability
    fun setAbility(value : String) = _ability.postValue(value)

    val startSelectPokemon = SingleLiveEvent<Any>()
    fun onSelectPokemon() = startSelectPokemon.call()

    val startSetSpinner = SingleLiveEvent<Any>()
    fun onSetSpinner() {
        startSetSpinner.call()
    }

    val startComplete = SingleLiveEvent<Any>()
    fun onComplete() = startComplete.call()
}