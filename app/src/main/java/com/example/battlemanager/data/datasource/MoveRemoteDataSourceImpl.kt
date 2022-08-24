package com.example.battlemanager.data.datasource

import com.example.battlemanager.data.api.MoveService
import com.example.battlemanager.data.model.MoveResponse
import javax.inject.Inject

class MoveRemoteDataSourceImpl @Inject constructor(private val service: MoveService) :
    MoveRemoteDataSource {
    override fun getMoveByName(name: String): MoveResponse {
        return service.getMoveForName(name).execute().body()!!
    }

    override fun getMoves(): List<MoveResponse> {
        return service.getMoves().execute().body()!!
    }

    override fun getMoveNameList(): List<String> {
        return service.getMoveNameList().execute().body()!!
    }

}