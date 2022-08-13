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
    var maxHp: Int = 0

    fun attack(other: Pokemon) {
        // TODO: 나중에 구현
    }

    override fun toString(): String {
        return "level: $level 특성: ${ability.name} 아이템: ${item.name} 성격: $nature\n" +
                effortValues.toString() + "\n" + individualValues.toString() + "\n" + rankStates.toString() + "\n" +
                " 상태이상: $statusAbnormality Hp: $hp"
    }
}