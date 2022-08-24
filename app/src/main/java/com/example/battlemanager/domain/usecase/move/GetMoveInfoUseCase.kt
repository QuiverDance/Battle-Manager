package com.example.battlemanager.domain.usecase.move

import com.example.battlemanager.data.repository.MoveRepositoryImpl
import com.example.battlemanager.domain.model.MoveInfo
import com.example.battlemanager.domain.model.PokemonInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMoveInfoUseCase @Inject constructor(private val moveRepository: MoveRepositoryImpl) {
    suspend fun invoke(name : String) : MoveInfo{
        val move = withContext(Dispatchers.IO){
            moveRepository.getMoveInfoByName(name)
        }
        return move
    }
}