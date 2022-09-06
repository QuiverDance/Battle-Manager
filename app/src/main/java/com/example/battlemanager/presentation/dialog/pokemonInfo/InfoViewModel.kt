package com.example.battlemanager.presentation.dialog.pokemonInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.battlemanager.domain.model.CategoryItem
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.domain.model.Pokemon
import com.example.battlemanager.presentation.global.util.SingleLiveEvent

class InfoViewModel : ViewModel() {
    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon : LiveData<Pokemon> get() = _pokemon
    fun setPokemon(pokemon: Pokemon){
        _pokemon.postValue(pokemon)
    }
}