package com.example.battlemanager.data.mapper

import com.example.battlemanager.data.model.PokemonResponse
import com.example.battlemanager.domain.model.PokemonInfo

object PokemonMapper {
    fun mapperToPokemonInfo(pokemonResponse: PokemonResponse) : PokemonInfo{
        return PokemonInfo(
            dexId = pokemonResponse.dexId,
            name = pokemonResponse.name,
            imageUrl = pokemonResponse.imageUrl,
            typeList = pokemonResponse.typeList,
            validAbilityList = pokemonResponse.validAbilityList,
            validGenderList = pokemonResponse.validGenderList,
            weight = pokemonResponse.weight
        )
    }
    fun mapperToPokemonResponse(pokemonInfo: PokemonInfo) : PokemonResponse{
        return PokemonResponse(
            dexId = pokemonInfo.dexId,
            name = pokemonInfo.name,
            imageUrl = pokemonInfo.imageUrl,
            typeList = pokemonInfo.typeList,
            validAbilityList = pokemonInfo.validAbilityList,
            validGenderList = pokemonInfo.validGenderList,
            weight = pokemonInfo.weight
        )
    }

}