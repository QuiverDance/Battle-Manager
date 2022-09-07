package com.example.battlemanager.domain.usecase.pokemon

import com.example.battlemanager.data.repository.PokemonRepositoryImpl
import com.example.battlemanager.domain.model.PokemonInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPokemonInfoUseCase @Inject constructor(private val pokemonRepository: PokemonRepositoryImpl) {
    suspend fun invoke(name : String) : PokemonInfo{
        val pokemon = withContext(Dispatchers.IO){
            pokemonRepository.getPokemonInfoByName(name)
        }
        return pokemon
    }
}