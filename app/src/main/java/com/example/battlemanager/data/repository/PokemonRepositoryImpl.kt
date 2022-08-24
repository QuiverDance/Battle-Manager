package com.example.battlemanager.data.repository

import com.example.battlemanager.data.datasource.PokemonRemoteDataSource
import com.example.battlemanager.data.datasource.PokemonRemoteDataSourceImpl
import com.example.battlemanager.data.mapper.PokemonMapper
import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val dataSource: PokemonRemoteDataSourceImpl) : PokemonRepository {
    override fun getPokemonInfoByName(name: String): PokemonInfo {
        return PokemonMapper.mapperToPokemonInfo(dataSource.getPokemonForName(name))
    }

    override fun getPokemonInfoList(): List<PokemonInfo> {
        return dataSource.getPokemons().map {
            PokemonMapper.mapperToPokemonInfo(it)
        }
    }

    override fun getPokemonNameList(): List<String> {
        return dataSource.getPokemonNameList()
    }
}