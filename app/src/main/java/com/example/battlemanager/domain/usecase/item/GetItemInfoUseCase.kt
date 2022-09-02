package com.example.battlemanager.domain.usecase.item

import com.example.battlemanager.data.repository.ItemRepositoryImpl
import com.example.battlemanager.domain.model.ItemInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetItemInfoUseCase @Inject constructor(private val itemRepository: ItemRepositoryImpl) {
    suspend fun invoke(name: String): ItemInfo {
        val item = withContext(Dispatchers.IO) {
            itemRepository.getItemInfoByName(name)
        }
        return item
    }
}