package com.example.battlemanager.domain.usecase.pokemon

import com.example.battlemanager.data.repository.PokemonRepositoryImpl
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonFilterListUseCase @Inject constructor(private val pokemonRepository: PokemonRepositoryImpl) {
    suspend fun invoke() : List<FilterItem>{
//        val pokemons = listOf(
//            FilterItem(1, "", "1.이상해씨"), FilterItem(2, "", "2.이상해풀"), FilterItem(3, "", "3.이상해꽃"),
//            FilterItem(4, "", "4.파이리"), FilterItem(5, "", "5.리자드"), FilterItem(6, "", "6.리자몽"),
//            FilterItem(7, "", "7.꼬부기"), FilterItem(8, "", "8.어니부기"), FilterItem(9, "", "9.거북왕")
//        )
//        //return repository.getPokemonNameList()
        val pokemonList = pokemonRepository.getPokemonInfoList()
        val filterList = mutableListOf<FilterItem>()
        for(pokemon in pokemonList)
            filterList.add(FilterItem(pokemon.id, pokemon.imageUrl, pokemon.name))
        return filterList
    }
}