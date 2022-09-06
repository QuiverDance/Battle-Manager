package com.example.battlemanager.presentation.pokemonSetting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.battlemanager.domain.model.*
import com.example.battlemanager.domain.usecase.item.GetItemFilterListUseCase
import com.example.battlemanager.domain.usecase.item.GetItemInfoUseCase
import com.example.battlemanager.domain.usecase.move.GetMoveFilterListUseCase
import com.example.battlemanager.domain.usecase.move.GetMoveInfoUseCase
import com.example.battlemanager.domain.usecase.pokemon.GetPokemonFilterListUseCase
import com.example.battlemanager.domain.usecase.pokemon.GetPokemonInfoUseCase
import com.example.battlemanager.presentation.global.base.BaseViewModel
import com.example.battlemanager.presentation.global.util.GenderUtil
import com.example.battlemanager.presentation.global.util.NatureUtil
import com.example.battlemanager.presentation.global.util.SingleLiveEvent
import com.example.battlemanager.presentation.global.util.StatusAbnormalityUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonSettingViewModel @Inject constructor(
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase,
    private val getPokemonFilterListUseCase: GetPokemonFilterListUseCase,
    private val getMoveInfoUseCase: GetMoveInfoUseCase,
    private val getMoveFilterListUseCase: GetMoveFilterListUseCase,
    private val getItemInfoUseCase: GetItemInfoUseCase,
    private val getItemFilterListUseCase: GetItemFilterListUseCase
) : BaseViewModel() {

    private val _pokemonInfo = MutableLiveData<PokemonInfo>()
    val pokemonInfo: LiveData<PokemonInfo> get() = _pokemonInfo
    fun getPokemonInfo(name: String) {
        viewModelScope.launch {
            val pokemonInfo = withContext(Dispatchers.IO) {
                getPokemonInfoUseCase.invoke(name)
            }
            _pokemonInfo.value = pokemonInfo
            startSetSpinner.call()
        }
    }

    private val _pokemonFilterList = MutableLiveData<List<FilterItem>>(null)
    val pokemonFilterList: LiveData<List<FilterItem>> get() = _pokemonFilterList
    val endGetPokemonList = SingleLiveEvent<Any>()
    fun getPokemonFilterList() {
        if (_pokemonFilterList.value != null) {
            endGetPokemonList.call()
            return
        }
        viewModelScope.launch {
            val filterList = withContext(Dispatchers.IO) {
                getPokemonFilterListUseCase.invoke()
            }
            _pokemonFilterList.value = filterList
            endGetPokemonList.call()
        }
    }

    private val _moveFilterList = MutableLiveData<List<FilterItem>>(null)
    val moveFilterList: LiveData<List<FilterItem>> get() = _moveFilterList
    val endGetMoveList = SingleLiveEvent<Any>()
    fun getMoveFilterList() {
        if (_moveFilterList.value != null) {
            endGetMoveList.call()
            return
        }
        viewModelScope.launch {
            val filterList = withContext(Dispatchers.IO) {
                getMoveFilterListUseCase.invoke()
            }
            _moveFilterList.value = filterList
            endGetMoveList.call()
        }
    }

    private val _itemInfo = MutableLiveData<ItemInfo>(ItemInfo(0, "없음", ""))
    val itemInfo: LiveData<ItemInfo> get() = _itemInfo
    fun getItemInfo(name: String) {
        viewModelScope.launch {
            val itemInfo = withContext(Dispatchers.IO) {
                getItemInfoUseCase.invoke(name)
            }
            _itemInfo.value = itemInfo
        }
    }

    private val _itemFilterList = MutableLiveData<List<FilterItem>>(null)
    val itemFilterList: LiveData<List<FilterItem>> get() = _itemFilterList
    val endGetItemList = SingleLiveEvent<Any>()
    fun getItemFilterList() {
        if (_itemFilterList.value != null) {
            endGetItemList.call()
            return
        }
        viewModelScope.launch {
            val filterList = withContext(Dispatchers.IO) {
                getItemFilterListUseCase.invoke()
            }
            _itemFilterList.value = filterList
            endGetItemList.call()
        }
    }

    val level = MutableLiveData("50")
    val hp = MutableLiveData(0)
    val hpText = MutableLiveData("0")
    val ivH = MutableLiveData("0")
    val ivA = MutableLiveData("0")
    val ivB = MutableLiveData("0")
    val ivC = MutableLiveData("0")
    val ivD = MutableLiveData("0")
    val ivS = MutableLiveData("0")

    val evH = MutableLiveData("0")
    val evA = MutableLiveData("0")
    val evB = MutableLiveData("0")
    val evC = MutableLiveData("0")
    val evD = MutableLiveData("0")
    val evS = MutableLiveData("0")

    val rankA = MutableLiveData("0")
    val rankB = MutableLiveData("0")
    val rankC = MutableLiveData("0")
    val rankD = MutableLiveData("0")
    val rankS = MutableLiveData("0")
    val rankAcc = MutableLiveData("0")
    val rankEva = MutableLiveData("0")
    val rankCri = MutableLiveData("0")

    private val _gender = MutableLiveData<String>()
    val gender: LiveData<String> get() = _gender
    fun setGender(id: Int) = _gender.postValue(GenderUtil.genderToString(id))

    private val _ability = MutableLiveData<String>()
    val ability: LiveData<String> get() = _ability
    fun setAbility(value: String) = _ability.postValue(value)

    private val _statusAbnormality = MutableLiveData<String>()
    val statusAbnormality: LiveData<String> get() = _statusAbnormality
    fun setStatusAbnormality(value: String) = _statusAbnormality.postValue(value)

    private val _nature = MutableLiveData<String>()
    val nature: LiveData<String> get() = _nature
    fun setNature(value: String) = _nature.postValue(value)

    private val _moveList =
        MutableLiveData(MutableList(4) { MoveInfo(0, "미설정", 0, "없음", "없음", 0) })
    val moveList: LiveData<MutableList<MoveInfo>> get() = _moveList
    fun setMoveInfo(name: String, pos: Int) {
        viewModelScope.launch {
            val moveInfo = withContext(Dispatchers.IO) {
                getMoveInfoUseCase.invoke(name)
            }
            val moves = _moveList.value!!
            moves[pos] = moveInfo
            _moveList.value = moves
        }
    }

    fun makePokemon(): Pokemon {
        return Pokemon(
            pokemonInfo.value!!,
            level.value!!.toInt(),
            AbilityInfo(0, ability.value!!, ""),
            _itemInfo.value!!,
            _moveList.value!!,
            NatureUtil.getNatureId(_nature.value!!),
            getEffortValues(),
            getIndividualValues(),
            getRankStates(),
            StatusAbnormalityUtil.getStatusAbnormalityId(statusAbnormality.value!!),
            hp.value!!,
            _gender.value!!
        )
    }

    private fun getEffortValues(): EffortValues {
        val evList = mutableListOf(
            evH.value!!,
            evA.value!!,
            evB.value!!,
            evC.value!!,
            evD.value!!,
            evS.value!!
        )

        for ((idx, value) in evList.withIndex()) {
            if (value == "")
                evList[idx] = "0"
        }

        return EffortValues(
            evList[0].toInt(),
            evList[1].toInt(),
            evList[2].toInt(),
            evList[3].toInt(),
            evList[4].toInt(),
            evList[5].toInt()
        )
    }

    private fun getIndividualValues(): IndividualValues {
        val ivList = mutableListOf(
            ivH.value!!,
            ivA.value!!,
            ivB.value!!,
            ivC.value!!,
            ivD.value!!,
            ivS.value!!
        )

        for ((idx, value) in ivList.withIndex()) {
            if (value == "")
                ivList[idx] = "0"
        }

        return IndividualValues(
            ivList[0].toInt(),
            ivList[1].toInt(),
            ivList[2].toInt(),
            ivList[3].toInt(),
            ivList[4].toInt(),
            ivList[5].toInt()
        )
    }

    private fun getRankStates(): RankStates {
        val rankList = mutableListOf(
            rankA.value!!,
            rankB.value!!,
            rankC.value!!,
            rankD.value!!,
            rankS.value!!,
            rankAcc.value!!,
            rankEva.value!!,
            rankCri.value!!
        )

        for ((idx, value) in rankList.withIndex()) {
            if (value == "")
                rankList[idx] = "0"
        }

        return RankStates(
            rankList[0].toInt(),
            rankList[1].toInt(),
            rankList[2].toInt(),
            rankList[3].toInt(),
            rankList[4].toInt(),
            rankList[5].toInt(),
            rankList[6].toInt(),
            rankList[7].toInt(),
        )
    }

    val startSelectPokemon = SingleLiveEvent<Any>()
    fun onSelectPokemon() = startSelectPokemon.call()

    val startSelectItem = SingleLiveEvent<Any>()
    fun onSelectItem() = startSelectItem.call()

    val startSetSpinner = SingleLiveEvent<Any>()
    fun onSetSpinner() = startSetSpinner.call()

    val selectedMove = MutableLiveData(-1)
    fun onSetMove(pos: Int) {
        selectedMove.value = pos
    }

    fun initPokemonInfo(pokemon: Pokemon){
        _pokemonInfo.postValue(pokemon.pokemonInfo)
        _ability.postValue(pokemon.ability.name)
        _gender.postValue(pokemon.gender)
        _itemInfo.postValue(pokemon.item)
        _nature.postValue(NatureUtil.natureToString(pokemon.nature))
        _statusAbnormality.postValue(StatusAbnormalityUtil.StatusAbnormalityToString(pokemon.statusAbnormality))
        level.postValue(pokemon.level.toString())
        ivH.postValue(pokemon.individualValues.H.toString())
        ivA.postValue(pokemon.individualValues.A.toString())
        ivB.postValue(pokemon.individualValues.B.toString())
        ivC.postValue(pokemon.individualValues.C.toString())
        ivD.postValue(pokemon.individualValues.D.toString())
        ivS.postValue(pokemon.individualValues.S.toString())

        evH.postValue(pokemon.effortValues.H.toString())
        evA.postValue(pokemon.effortValues.A.toString())
        evB.postValue(pokemon.effortValues.B.toString())
        evC.postValue(pokemon.effortValues.C.toString())
        evD.postValue(pokemon.effortValues.D.toString())
        evS.postValue(pokemon.effortValues.S.toString())

        rankA.postValue(pokemon.rankStates.attack.toString())
        rankB.postValue(pokemon.rankStates.defense.toString())
        rankC.postValue(pokemon.rankStates.specialAttack.toString())
        rankD.postValue(pokemon.rankStates.specialDefense.toString())
        rankS.postValue(pokemon.rankStates.speed.toString())
        rankAcc.postValue(pokemon.rankStates.accuracy.toString())
        rankEva.postValue(pokemon.rankStates.evasionRate.toString())
        rankCri.postValue(pokemon.rankStates.criticalRate.toString())

        for(i in 0..3)
            _moveList.value!![i] = pokemon.moves[i]
    }
}