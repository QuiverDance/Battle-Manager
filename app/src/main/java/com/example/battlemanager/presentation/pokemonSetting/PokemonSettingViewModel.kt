package com.example.battlemanager.presentation.pokemonSetting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.battlemanager.domain.model.*
import com.example.battlemanager.domain.usecase.move.GetMoveFilterListUseCase
import com.example.battlemanager.domain.usecase.move.GetMoveInfoUseCase
import com.example.battlemanager.domain.usecase.pokemon.GetPokemonFilterListUseCase
import com.example.battlemanager.domain.usecase.pokemon.GetPokemonInfoUseCase
import com.example.battlemanager.presentation.global.constant.Nature
import com.example.battlemanager.presentation.global.util.GenderUtil
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
    private val getMoveFilterListUseCase: GetMoveFilterListUseCase
) : ViewModel() {

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

    val level = MutableLiveData<String>("50")
    val hp = MutableLiveData<Int>(0)
    val hpText = MutableLiveData<String>("0")
    val ivH = MutableLiveData<String>("0")
    val ivA = MutableLiveData<String>("0")
    val ivB = MutableLiveData<String>("0")
    val ivC = MutableLiveData<String>("0")
    val ivD = MutableLiveData<String>("0")
    val ivS = MutableLiveData<String>("0")

    val evH = MutableLiveData<String>("0")
    val evA = MutableLiveData<String>("0")
    val evB = MutableLiveData<String>("0")
    val evC = MutableLiveData<String>("0")
    val evD = MutableLiveData<String>("0")
    val evS = MutableLiveData<String>("0")

    val rankA = MutableLiveData<String>("0")
    val rankB = MutableLiveData<String>("0")
    val rankC = MutableLiveData<String>("0")
    val rankD = MutableLiveData<String>("0")
    val rankS = MutableLiveData<String>("0")
    val rankAcc = MutableLiveData<String>("0")
    val rankEva = MutableLiveData<String>("0")
    val rankCri = MutableLiveData<String>("0")

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
        MutableLiveData(MutableList<MoveInfo>(4) { MoveInfo(0, "미설정", 0, "없음", "없음", 0) })
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
            ItemInfo(0, "아이템", ""),
            _moveList.value!!,
            Nature.NULL,
            getEffortValues(),
            getIndividualValues(),
            getRankStates(),
            StatusAbnormalityUtil.getStatusAbnormalityId(statusAbnormality.value!!),
            hp.value!!,
            _gender.value!!
        )
    }

    private fun getEffortValues(): EffortValues {
        val evList = mutableListOf(evH.value!!, evA.value!!, evB.value!!, evC.value!!, evD.value!!, evS.value!!)

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
        val ivList = mutableListOf(ivH.value!!, ivA.value!!, ivB.value!!, ivC.value!!, ivD.value!!, ivS.value!!)

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
        val rankList = mutableListOf(rankA.value!!, rankB.value!!, rankC.value!!, rankD.value!!, rankS.value!!, rankAcc.value!!, rankEva.value!!, rankCri.value!!)

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

    val startSetSpinner = SingleLiveEvent<Any>()
    fun onSetSpinner() = startSetSpinner.call()

    val selectedMove = MutableLiveData(-1)
    fun onSetMove1() {
        selectedMove.value = 0
    }

    fun onSetMove2() {
        selectedMove.value = 1
    }

    fun onSetMove3() {
        selectedMove.value = 2
    }

    fun onSetMove4() {
        selectedMove.value = 3
    }

    val startComplete = SingleLiveEvent<Any>()
    fun onComplete() = startComplete.call()
}