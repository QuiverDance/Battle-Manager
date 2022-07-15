package com.example.battlemanager.domain.usecase

import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.PokemonRepository

class GetPokemonNameListUseCase(private val repository: PokemonRepository) {
    suspend fun invoke() : List<String>{
        return repository.getPokemonNameList()
    }
}