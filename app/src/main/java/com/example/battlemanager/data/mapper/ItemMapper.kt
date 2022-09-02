package com.example.battlemanager.data.mapper

import com.example.battlemanager.data.model.ItemResponse
import com.example.battlemanager.domain.model.ItemInfo

object ItemMapper {
    fun mapperToItemInfo(itemResponse: ItemResponse): ItemInfo {
        return ItemInfo(
            id = itemResponse.id,
            name = itemResponse.name,
            category = itemResponse.category
        )
    }
}