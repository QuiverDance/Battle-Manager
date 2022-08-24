package com.example.battlemanager.domain.usecase.move

import com.example.battlemanager.data.repository.MoveRepositoryImpl
import com.example.battlemanager.domain.model.FilterItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMoveFilterListUseCase @Inject constructor(private val moveRepository: MoveRepositoryImpl) {
    suspend fun invoke(): List<FilterItem> {
        val moveList = withContext(Dispatchers.IO) {
            moveRepository.getMoveInfoList()
        }
        val filterList = mutableListOf<FilterItem>()
        for (move in moveList) {
            filterList.add(FilterItem(move.id.toInt(), "", move.name))
        }
        return filterList
    }
}