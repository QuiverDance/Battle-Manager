package com.example.battlemanager.domain.repository

import com.example.battlemanager.domain.model.MoveInfo

interface MoveRepository {
    fun getMoveInfoByName(name: String): MoveInfo
    fun getMoveInfoList(): List<MoveInfo>
    fun getMoveNameList(): List<String>
}