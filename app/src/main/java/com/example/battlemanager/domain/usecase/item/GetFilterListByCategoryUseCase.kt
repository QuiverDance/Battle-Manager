package com.example.battlemanager.domain.usecase.item

import com.example.battlemanager.data.repository.ItemRepositoryImpl
import com.example.battlemanager.domain.model.FilterItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFilterListByCategoryUseCase @Inject constructor(private val itemRepository: ItemRepositoryImpl) {
    suspend fun invoke(): List<List<FilterItem>> {
        val itemList = withContext(Dispatchers.IO) {
            itemRepository.getItemInfoList()
        }
        val filterList = mutableListOf<MutableList<FilterItem>>()
        for (item in itemList) {
            filterList[getCorrectPosition(filterList, item.category)]
                .add(FilterItem(item.id.toInt(), "", item.name, item.category))
        }
        return filterList
    }

    private fun getCorrectPosition(list: List<List<FilterItem>>, category: String): Int {
        var pos = list.size
        for ((idx, li) in list.withIndex()) {
            if (li.isNotEmpty() && li[0].type == category) {
                pos = idx
                break
            }
        }
        return pos
    }
}