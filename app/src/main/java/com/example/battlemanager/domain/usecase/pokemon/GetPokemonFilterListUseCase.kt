package com.example.battlemanager.domain.usecase.pokemon

import android.util.Log
import com.example.battlemanager.data.repository.PokemonRepositoryImpl
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPokemonFilterListUseCase @Inject constructor(private val pokemonRepository: PokemonRepositoryImpl) {
    suspend fun invoke() : List<FilterItem>{
        val pokemonList = withContext(Dispatchers.IO){
            pokemonRepository.getPokemonInfoList()
        }
        Log.d("포켓몬 리스트 size : ", pokemonList.size.toString())
        val pokemons = listOf(
            FilterItem(1, "", "이상해씨"), FilterItem(2, "", "이상해풀"), FilterItem(3, "", "이상해꽃"),
            FilterItem(4, "", "파이리"), FilterItem(5, "", "리자드"), FilterItem(6, "", "리자몽"),
            FilterItem(7, "", "꼬부기"), FilterItem(8, "", "어니부기"), FilterItem(9, "", "거북왕")
        )
        return pokemons
//        val filterList = mutableListOf<FilterItem>()
//        var id = 1
//        for(pokemon in pokemonList) {
//            filterList.add(FilterItem(id, "", pokemon))
//            id += 1
//        }
//        return filterList
    }
}