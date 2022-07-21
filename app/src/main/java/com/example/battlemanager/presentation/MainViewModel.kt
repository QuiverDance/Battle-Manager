package com.example.battlemanager.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.global.util.SingleLiveEvent

class MainViewModel : ViewModel() {

    private val _pokemon1 = MutableLiveData<FilterItem>(null)
    val pokemon1: LiveData<FilterItem> get() =_pokemon1
    fun setPokemon1(pokemon : FilterItem){
        _pokemon1.postValue(pokemon)
    }

    val startPokemon1 = SingleLiveEvent<Any>()

    fun startPokemon1() {
        startPokemon1.call()
    }
}