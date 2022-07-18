package com.example.battlemanager.domain.model

import com.example.battlemanager.global.constant.IndividualValue
import com.example.battlemanager.global.constant.Nature
import com.example.battlemanager.global.constant.StatusAbnormality

class Pokemon(private val pokemonInfo: PokemonInfo) {
    private var level = 50
    private lateinit var ability : Item
    private lateinit var item : Item
    private val moves = mutableListOf<Move>()
    private var nature = Nature.NONE
    private var effortValues = EffortValues(0, 0, 0, 0,0, 0)
    private var individualValues = IndividualValues(IndividualValue.L,IndividualValue.L,IndividualValue.L,IndividualValue.L,IndividualValue.L,IndividualValue.L)
    private var rankStates = RankStates(0, 0, 0, 0, 0, 0, 0, 0)
    private var statusAbnormality = StatusAbnormality.None
    
    fun attack(other : Pokemon){
        // TODO: 나중에 구현 
    }
}