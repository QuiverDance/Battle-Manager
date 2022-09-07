package com.example.battlemanager.data.mapper

import com.example.battlemanager.data.model.PokemonResponse
import com.example.battlemanager.domain.model.PokemonInfo

object PokemonMapper {
    fun mapperToPokemonInfo(pokemonResponse: PokemonResponse): PokemonInfo {
        val abilityList = mutableListOf(pokemonResponse.ability1)
        if (pokemonResponse.ability2 != null)
            abilityList.add(pokemonResponse.ability2)
        if (pokemonResponse.ability3 != null)
            abilityList.add(pokemonResponse.ability3)

        return PokemonInfo(
            id = pokemonResponse.id,
            dexId = pokemonResponse.dexId,
            name = pokemonResponse.name,
            imageUrl = pokemonResponse.imageUrl,
            type1 = pokemonResponse.type1,
            type2 = pokemonResponse.type2 ?: "",
            validAbilityList = abilityList,
            validGender = pokemonResponse.genderType,
            weight = pokemonResponse.weight,
            baseStats = listOf(
                pokemonResponse.h,
                pokemonResponse.a,
                pokemonResponse.b,
                pokemonResponse.c,
                pokemonResponse.d,
                pokemonResponse.s,
            )
        )
    }
}