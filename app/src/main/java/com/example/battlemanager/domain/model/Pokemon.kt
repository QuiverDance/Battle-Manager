package com.example.battlemanager.domain.model

class Pokemon(
    val pokemonInfo: PokemonInfo,
    val level: Int,
    val ability: AbilityInfo,
    val item: ItemInfo,
    val moves: List<MoveInfo>,
    val nature: Int,
    val effortValues: EffortValues,
    val individualValues: IndividualValues,
    val rankStates: RankStates,
    val statusAbnormality: Int,
    val hp: Int
) {

    fun attack(other: Pokemon) {
        // TODO: 나중에 구현
    }
}