package com.example.battlemanager.domain.usecase.pokemon

import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.PokemonRepository

class GetPokemonInfoUseCase(private val repository: PokemonRepository) {
    suspend fun invoke(id : Int) : PokemonInfo{
        return repository.getPokemonInfo(id)
    }
}