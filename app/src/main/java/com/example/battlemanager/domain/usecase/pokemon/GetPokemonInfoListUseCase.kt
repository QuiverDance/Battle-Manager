package com.example.battlemanager.domain.usecase.pokemon

import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.PokemonRepository

class GetPokemonInfoListUseCase(private val repository: PokemonRepository) {
    suspend fun invoke() : List<PokemonInfo>{
        return repository.getPokemonInfoList()
    }
}