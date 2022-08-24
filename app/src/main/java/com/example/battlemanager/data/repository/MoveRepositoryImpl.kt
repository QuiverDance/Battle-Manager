package com.example.battlemanager.data.repository

import com.example.battlemanager.data.datasource.MoveRemoteDataSourceImpl
import com.example.battlemanager.data.mapper.MoveMapper
import com.example.battlemanager.domain.model.MoveInfo
import com.example.battlemanager.domain.repository.MoveRepository
import javax.inject.Inject

class MoveRepositoryImpl @Inject constructor(private val dataSource: MoveRemoteDataSourceImpl) :
    MoveRepository {
    override fun getMoveInfoByName(name: String): MoveInfo {
        return MoveMapper.mapperToMoveInfo(dataSource.getMoveByName(name))
    }

    override fun getMoveInfoList(): List<MoveInfo> {
        return dataSource.getMoves().map {
            MoveMapper.mapperToMoveInfo(it)
        }
    }

    override fun getMoveNameList(): List<String> {
        return dataSource.getMoveNameList()
    }
}