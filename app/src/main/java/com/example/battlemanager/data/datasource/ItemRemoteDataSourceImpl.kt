package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.api.ItemService
import com.example.battlemanager.data.model.ItemResponse
import javax.inject.Inject

class ItemRemoteDataSourceImpl @Inject constructor(private val service: ItemService) :
    ItemRemoteDataSource {
    override fun getItemByName(name: String): ItemResponse {
        return service.getMoveForName(name).execute().body()!!
    }

    override fun getItems(): List<ItemResponse> {
        return service.getMoves().execute().body()!!
    }
}