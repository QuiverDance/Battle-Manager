package com.example.battlemanager.data.repository

import com.example.battlemanager.data.datasource.AbilityRemoteDataSource
import com.example.battlemanager.data.datasource.PokemonRemoteDataSource
import com.example.battlemanager.data.mapper.AbilityMapper
import com.example.battlemanager.data.mapper.PokemonMapper
import com.example.battlemanager.domain.model.AbilityInfo
import com.example.battlemanager.domain.model.PokemonInfo
import com.example.battlemanager.domain.repository.AbilityRepository
import com.example.battlemanager.domain.repository.PokemonRepository

class AbilityRepositoryImpl(private val dataSource: AbilityRemoteDataSource) : AbilityRepository {
    override suspend fun getAbilityInfo(id: Int): AbilityInfo {
        return AbilityMapper.mapperToAbilityInfo(dataSource.getAbility(id))
    }

    override suspend fun getAbilityInfoList(): List<AbilityInfo> {
        return dataSource.getAbilities().map {
            AbilityMapper.mapperToAbilityInfo(it)
        }
    }

}