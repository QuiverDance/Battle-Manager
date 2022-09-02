package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.model.ItemResponse

interface ItemRemoteDataSource {
    fun getItemByName(name: String) : ItemResponse
    fun getItems() : List<ItemResponse>
}