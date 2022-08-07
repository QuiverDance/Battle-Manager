package com.example.battlemanager.domain.usecase.pokemon

import com.example.battlemanager.domain.model.BaseStats
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.PokemonRepository

class GetPokemonInfoUseCase() {
    suspend fun invoke(id : Int) : PokemonInfo{
        val pokemons = listOf(
            PokemonInfo(1, 1, "이상해씨", "", "풀", "독", listOf("심록"), 3, 5f, BaseStats(45, 49, 49, 65, 65, 45)),
            PokemonInfo(2, 2, "이상해풀", "", "풀", "독", listOf("심록"), 3, 5f, BaseStats(60, 62, 63, 80, 80, 60)),
            PokemonInfo(3, 3, "이상해꽃", "", "풀", "독", listOf("심록"), 3, 5f, BaseStats(80, 82, 83, 100, 100, 80)),
            PokemonInfo(4, 4, "파이리", "", "불", "", listOf("맹화"), 3, 5f, BaseStats(39, 52, 43, 60, 50, 65)),
            PokemonInfo(5, 5, "리자드", "", "불", "", listOf("맹화"), 3, 5f, BaseStats(58, 64, 58, 80, 65, 80)),
            PokemonInfo(6, 6, "리자몽", "", "불", "비행", listOf("맹화"), 3, 5f, BaseStats(78, 84, 78, 109, 85, 100)),
            PokemonInfo(7, 7, "꼬부기", "", "물", "", listOf("급류"), 3, 5f, BaseStats(44, 48, 65, 50, 64, 43)),
            PokemonInfo(8, 8, "어니부기", "", "물", "", listOf("급류"), 3, 5f, BaseStats(59, 63, 80, 65, 80, 58)),
            PokemonInfo(9, 9, "거북왕", "", "물", "", listOf("급류"), 3, 5f, BaseStats(79, 83, 100, 85, 105, 78)),
        )
        //return repository.getPokemonInfo(id)
        return pokemons[id-1]
    }
}