package com.example.battlemanager.domain.usecase.item

import com.example.battlemanager.data.repository.ItemRepositoryImpl
import com.example.battlemanager.domain.model.FilterItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetItemFilterListUseCase @Inject constructor(private val itemRepository: ItemRepositoryImpl) {
    suspend fun invoke(): List<FilterItem> {
        val itemList = withContext(Dispatchers.IO) {
            itemRepository.getItemInfoList()
        }
        val filterList = mutableListOf<FilterItem>()
        for (item in itemList) {
            filterList.add(FilterItem(item.id.toInt(), "", item.name, ""))
        }
        return filterList
    }
}