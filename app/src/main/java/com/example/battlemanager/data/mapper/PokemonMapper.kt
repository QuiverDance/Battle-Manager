package com.example.battlemanager.data.mapper

import com.example.battlemanager.data.model.PokemonResponse
import com.example.battlemanager.domain.model.BaseStats
import com.example.battlemanager.domain.model.PokemonInfo

object PokemonMapper {
    fun mapperToPokemonInfo(pokemonResponse: PokemonResponse) : PokemonInfo{
        val abilityList = mutableListOf(pokemonResponse.ability1)
        if(pokemonResponse.ability2 != "null")
            abilityList.add(pokemonResponse.ability2)
        if(pokemonResponse.ability3 != "null")
            abilityList.add(pokemonResponse.ability3)

        return PokemonInfo(
            id = pokemonResponse.id,
            dexId = pokemonResponse.dexId,
            name = pokemonResponse.name,
            imageUrl = pokemonResponse.imgUrl,
            type1 = pokemonResponse.type1,
            type2 = pokemonResponse.type2,
            validAbilityList = abilityList,
            validGender = pokemonResponse.genderType,
            weight = pokemonResponse.weight,
            baseStats = BaseStats(
                H = pokemonResponse.h,
                A = pokemonResponse.a,
                B = pokemonResponse.b,
                C = pokemonResponse.c,
                D = pokemonResponse.d,
                S = pokemonResponse.s,
            )
        )
    }
}