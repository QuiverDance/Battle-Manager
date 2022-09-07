package com.example.battlemanager.data.mapper

import com.example.battlemanager.data.model.MoveResponse
import com.example.battlemanager.domain.model.MoveInfo

object MoveMapper {
    fun mapperToMoveInfo(moveResponse: MoveResponse): MoveInfo {
        return MoveInfo(
            id = moveResponse.id,
            name = moveResponse.name,
            power = moveResponse.power,
            type = moveResponse.type,
            category = moveResponse.category,
            accuracy = moveResponse.accuracy
        )
    }
}