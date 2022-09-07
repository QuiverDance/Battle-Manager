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
import com.example.battlemanager.presentation.global.util.*
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
            rightMenu1Enable.value = true
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

    val ivList = MutableList(6){MutableLiveData("0")}
    val evList = MutableList(6){MutableLiveData("0")}
    val rankList = MutableList(8){MutableLiveData("0")}

    private val _gender = MutableLiveData<String>()
    val gender: LiveData<String> get() = _gender
    val genderList = MutableLiveData<List<String>>()
    fun setGender(id: Int) = _gender.postValue(GenderUtil.genderToString(id))
    fun setGenderList() = genderList.postValue(GenderUtil.getGenderList(pokemonInfo.value!!.validGender))

    private val _ability = MutableLiveData<String>()
    val ability: LiveData<String> get() = _ability
    val abilityList = MutableLiveData<List<String>>()
    fun setAbility(value: String) = _ability.postValue(value)
    fun setAbilityList() = abilityList.postValue(pokemonInfo.value!!.validAbilityList)

    private val _statusAbnormality = MutableLiveData<String>()
    val statusAbnormality: LiveData<String> get() = _statusAbnormality
    val statusAbnormalityList = MutableLiveData<List<String>>()
    fun setStatusAbnormality(value: String) = _statusAbnormality.postValue(value)
    fun setStatusAbnormalityList() = statusAbnormalityList.postValue(StatusAbnormalityUtil.getStatusAbnormalityList())

    private val _nature = MutableLiveData<String>()
    val nature: LiveData<String> get() = _nature
    val natureList = MutableLiveData<List<String>>()
    fun setNature(value: String) = _nature.postValue(value)
    fun setNatureList() = natureList.postValue(NatureUtil.getNatureList())

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

    private fun getEffortValues(): List<Int> {
        return listOf(
            evList[0].value!!.toInt(),
            evList[1].value!!.toInt(),
            evList[2].value!!.toInt(),
            evList[3].value!!.toInt(),
            evList[4].value!!.toInt(),
            evList[5].value!!.toInt()
        )
    }

    private fun getIndividualValues(): List<Int> {
        return listOf(
            ivList[0].value!!.toInt(),
            ivList[1].value!!.toInt(),
            ivList[2].value!!.toInt(),
            ivList[3].value!!.toInt(),
            ivList[4].value!!.toInt(),
            ivList[5].value!!.toInt()
        )
    }

    private fun getRankStates(): List<Int> {
        return listOf(
            0,
            rankList[0].value!!.toInt(),
            rankList[1].value!!.toInt(),
            rankList[2].value!!.toInt(),
            rankList[3].value!!.toInt(),
            rankList[4].value!!.toInt(),
            rankList[5].value!!.toInt(),
            rankList[6].value!!.toInt(),
            rankList[7].value!!.toInt()
        )
    }

    val startSelectPokemon = SingleLiveEvent<Any>()
    fun onSelectPokemon() = startSelectPokemon.call()

    val startSelectItem = SingleLiveEvent<Any>()
    fun onSelectItem() = startSelectItem.call()

    val startSetSpinner = SingleLiveEvent<Any>()

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

        for(i in 0..5){
            ivList[i].postValue(pokemon.individualValues[i].toString())
            evList[i].postValue(pokemon.effortValues[i].toString())
        }
        for(i in 1..7){
            rankList[i-1].postValue(pokemon.rankStates[i].toString())
        }
        for(i in 0..3)
            _moveList.value!![i] = pokemon.moves[i]
    }
}