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
    val hp: Int,
    val gender: String
) {
    var maxHp: Int = 0

    fun attack(other: Pokemon) {
        // TODO: 나중에 구현
    }

    override fun toString(): String {
        return "포켓몬 정보: $pokemonInfo \n level: $level 특성: ${ability.name} 아이템: ${item.name} 성격: $nature\n" +
                "노력치: $effortValues" + "\n" + "개체값: $individualValues" + "\n" + "랭크업: $rankStates" + "\n" +
                " 상태이상: $statusAbnormality Hp: $hp 성별 : $gender"
    }
}