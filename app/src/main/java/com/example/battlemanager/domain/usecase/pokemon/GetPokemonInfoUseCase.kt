package com.example.battlemanager.domain.usecase.pokemon

import com.example.battlemanager.domain.model.BaseStats
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.PokemonRepository

class GetPokemonInfoUseCase(private val repository: PokemonRepository) {
    suspend fun invoke(id : Int) : PokemonInfo{
        val pokemons = listOf(
            PokemonInfo(1, 1, "이상해씨", "", listOf("풀", "독"), listOf("심록"), 3, 5f, BaseStats(50, 50, 50, 50, 50, 50)),
            PokemonInfo(2, 2, "이상해풀", "", listOf("풀", "독"), listOf("심록"), 3, 5f, BaseStats(50, 50, 50, 50, 50, 50)),
            PokemonInfo(3, 3, "이상해꽃", "", listOf("풀", "독"), listOf("심록"), 3, 5f, BaseStats(50, 50, 50, 50, 50, 50)),
            PokemonInfo(4, 4, "파이리", "", listOf("불"), listOf("맹화"), 3, 5f, BaseStats(50, 50, 50, 50, 50, 50)),
            PokemonInfo(5, 5, "리자드", "", listOf("불"), listOf("맹화"), 3, 5f, BaseStats(50, 50, 50, 50, 50, 50)),
            PokemonInfo(6, 6, "리자몽", "", listOf("불", "비행"), listOf("맹화"), 3, 5f, BaseStats(50, 50, 50, 50, 50, 50)),
            PokemonInfo(7, 7, "꼬부기", "", listOf("물"), listOf("급류"), 3, 5f, BaseStats(50, 50, 50, 50, 50, 50)),
            PokemonInfo(8, 8, "어니부기", "", listOf("물"), listOf("급류"), 3, 5f, BaseStats(50, 50, 50, 50, 50, 50)),
            PokemonInfo(9, 9, "거북왕", "", listOf("물"), listOf("급류"), 3, 5f, BaseStats(50, 50, 50, 50, 50, 50)),
        )
        //return repository.getPokemonInfo(id)
        return pokemons[id]
    }
}