package com.example.battlemanager.data.repository

import com.example.battlemanager.data.datasource.PokemonRemoteDataSource
import com.example.battlemanager.data.mapper.PokemonMapper
import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val dataSource: PokemonRemoteDataSource) : PokemonRepository {
    override suspend fun getPokemonInfoForName(name: String): PokemonInfo {
        return PokemonMapper.mapperToPokemonInfo(dataSource.getPokemonForName(name))
    }

    override suspend fun getPokemonInfoList(): List<PokemonInfo> {
        return dataSource.getPokemons().map {
            PokemonMapper.mapperToPokemonInfo(it)
        }
    }

    override suspend fun getPokemonNameList(): List<String> {
        return dataSource.getPokemonNameList()
    }
}