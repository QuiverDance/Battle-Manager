package com.example.battlemanager.domain.repository

import com.example.battlemanager.domain.model.ItemInfo

interface ItemRepository {
    fun getItemInfoByName(name: String): ItemInfo
    fun getItemInfoList(): List<ItemInfo>
}