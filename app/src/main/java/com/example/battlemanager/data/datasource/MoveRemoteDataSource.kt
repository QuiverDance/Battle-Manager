package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.model.MoveResponse

interface MoveRemoteDataSource {
    fun getMoveByName(name: String) : MoveResponse
    fun getMoves() : List<MoveResponse>
    fun getMoveNameList() : List<String>
}