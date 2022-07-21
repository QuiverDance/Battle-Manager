package com.example.battlemanager.domain.usecase.pokemon

import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.PokemonRepository

class GetPokemonNameListUseCase() {
    suspend fun invoke() : List<FilterItem>{
        val pokemons = listOf(
            FilterItem(1, "", "1.이상해씨"), FilterItem(2, "", "2.이상해풀"), FilterItem(3, "", "3.이상해꽃"),
            FilterItem(4, "", "4.파이리"), FilterItem(5, "", "5.리자드"), FilterItem(6, "", "6.리자몽"),
            FilterItem(7, "", "7.꼬부기"), FilterItem(8, "", "8.어니부기"), FilterItem(9, "", "9.거북왕")
        )
        //return repository.getPokemonNameList()
        return pokemons
    }
}