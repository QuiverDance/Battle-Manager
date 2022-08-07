package com.example.battlemanager.data.mapper

import com.example.battlemanager.data.model.PokemonResponse
import com.example.battlemanager.domain.model.BaseStats
import com.example.battlemanager.domain.model.PokemonInfo

object PokemonMapper {
    fun mapperToPokemonInfo(pokemonResponse: PokemonResponse) : PokemonInfo{
        return PokemonInfo(
            id = pokemonResponse.id,
            dexId = pokemonResponse.dexId,
            name = pokemonResponse.name,
            imageUrl = pokemonResponse.imageUrl,
            type1 = pokemonResponse.type1,
            type2 = pokemonResponse.type2,
            validAbilityList = pokemonResponse.validAbilityList,
            validGender = pokemonResponse.validGender,
            weight = pokemonResponse.weight,
            baseStats = BaseStats(
                H = pokemonResponse.baseStats.H,
                A = pokemonResponse.baseStats.A,
                B = pokemonResponse.baseStats.B,
                C = pokemonResponse.baseStats.C,
                D = pokemonResponse.baseStats.D,
                S = pokemonResponse.baseStats.S,
            )
        )
    }
    fun mapperToPokemonResponse(pokemonInfo: PokemonInfo) : PokemonResponse{
        return PokemonResponse(
            id = pokemonInfo.id,
            dexId = pokemonInfo.dexId,
            name = pokemonInfo.name,
            imageUrl = pokemonInfo.imageUrl,
            type1 = pokemonInfo.type1,
            type2 = pokemonInfo.type2,
            validAbilityList = pokemonInfo.validAbilityList,
            validGender = pokemonInfo.validGender,
            weight = pokemonInfo.weight,
            baseStats = com.example.battlemanager.data.model.BaseStats(
                H = pokemonInfo.baseStats.H,
                A = pokemonInfo.baseStats.A,
                B = pokemonInfo.baseStats.B,
                C = pokemonInfo.baseStats.C,
                D = pokemonInfo.baseStats.D,
                S = pokemonInfo.baseStats.S,
            )
        )
    }

}