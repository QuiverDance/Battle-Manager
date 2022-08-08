package com.example.battlemanager.domain.model

class Pokemon(
    val pokemonInfo: PokemonInfo,
    val level: Int,
    val ability: Ability,
    val item: Item,
    val moves: List<Move>,
    val nature: Int,
    val effortValues: EffortValues,
    val individualValues: IndividualValues,
    val rankStates: RankStates,
    val statusAbnormality: Int
) {

    fun attack(other: Pokemon) {
        // TODO: 나중에 구현
    }
}