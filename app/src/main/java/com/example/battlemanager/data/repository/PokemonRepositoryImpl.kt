package com.example.battlemanager.data.repository

import com.example.battlemanager.data.datasource.PokemonRemoteDataSource
import com.example.battlemanager.data.mapper.PokemonMapper
import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.PokemonRepository

class PokemonRepositoryImpl(private val dataSource: PokemonRemoteDataSource) : PokemonRepository {
    override suspend fun getPokemonInfo(id: Int): PokemonInfo {
        return PokemonMapper.mapperToPokemonInfo(dataSource.getPokemon(id))
    }

    override suspend fun getPokemonInfoList(): List<PokemonInfo> {
        return dataSource.getPokemons().map {
            PokemonMapper.mapperToPokemonInfo(it)
        }
    }

    override suspend fun getPokemonNameList(): List<String> {
        return dataSource.getPokemonsName()
    }
}