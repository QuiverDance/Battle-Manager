package com.example.battlemanager.data.repository

import com.example.battlemanager.data.datasource.ItemRemoteDataSourceImpl
import com.example.battlemanager.data.datasource.MoveRemoteDataSourceImpl
import com.example.battlemanager.data.mapper.ItemMapper
import com.example.battlemanager.data.mapper.MoveMapper
import com.example.battlemanager.domain.model.ItemInfo
import com.example.battlemanager.domain.model.MoveInfo
import com.example.battlemanager.domain.repository.ItemRepository
import com.example.battlemanager.domain.repository.MoveRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(private val dataSource: ItemRemoteDataSourceImpl) :
    ItemRepository {
    override fun getItemInfoByName(name: String): ItemInfo {
        return ItemMapper.mapperToItemInfo(dataSource.getItemByName(name))
    }

    override fun getItemInfoList(): List<ItemInfo> {
        return dataSource.getItems().map {
            ItemMapper.mapperToItemInfo(it)
        }
    }
}