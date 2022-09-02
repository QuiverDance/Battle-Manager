package com.example.battlemanager.data.mapper

import android.util.Log
import com.example.battlemanager.data.model.MoveResponse
import com.example.battlemanager.data.model.PokemonResponse
import com.example.battlemanager.domain.model.BaseStats
import com.example.battlemanager.domain.model.MoveInfo
import com.example.battlemanager.domain.model.PokemonInfo

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