package com.example.battlemanager.domain.usecase.pokemon

import android.util.Log
import com.example.battlemanager.data.repository.PokemonRepositoryImpl
import com.example.battlemanager.domain.model.FilterItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPokemonFilterListUseCase @Inject constructor(private val pokemonRepository: PokemonRepositoryImpl) {
    suspend fun invoke(): List<FilterItem> {
        val pokemonList = withContext(Dispatchers.IO) {
            pokemonRepository.getPokemonInfoList()
        }
        val filterList = mutableListOf<FilterItem>()
        for (pokemon in pokemonList) {
            filterList.add(FilterItem(pokemon.id.toInt(), pokemon.imageUrl, pokemon.name, pokemon.type1))
        }
        return filterList
    }
}