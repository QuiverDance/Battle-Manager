package com.example.battlemanager.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.battlemanager.domain.model.*
import com.example.battlemanager.domain.usecase.pokemon.GetPokemonInfoUseCase
import com.example.battlemanager.domain.usecase.pokemon.GetPokemonFilterListUseCase
import com.example.battlemanager.global.constant.Nature
import com.example.battlemanager.global.util.GenderUtil
import com.example.battlemanager.global.util.SingleLiveEvent
import com.example.battlemanager.global.util.StatusAbnormalityUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonSettingViewModel : ViewModel() {
    @Inject
    lateinit var getPokemonInfoUseCase: GetPokemonInfoUseCase
    @Inject
    lateinit var getPokemonNameListUseCase: GetPokemonFilterListUseCase

    private val _pokemonInfo = MutableLiveData<PokemonInfo>()
    val pokemonInfo: LiveData<PokemonInfo> get() = _pokemonInfo
    fun getPokemonInfo(name : String){
        viewModelScope.launch {
            val pokemonInfo = withContext(Dispatchers.IO){
                getPokemonInfoUseCase.invoke(name)
            }
            _pokemonInfo.value = pokemonInfo
            startSetSpinner.call()
        }
    }

    private val _pokemonNameList = MutableLiveData<List<FilterItem>>(null)
    val pokemonNameList : LiveData<List<FilterItem>> get() = _pokemonNameList
    fun getPokemonNameList(){
        viewModelScope.launch {
            val nameList = withContext(Dispatchers.IO){
                getPokemonNameListUseCase.invoke()
            }
            _pokemonNameList.value = nameList
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
    val gender: LiveData<String> get() =_gender
    fun setGender(id : Int) = _gender.postValue(GenderUtil.genderToString(id))

    private val _ability = MutableLiveData<String>()
    val ability: LiveData<String> get() =_ability
    fun setAbility(value : String) = _ability.postValue(value)

    private val _statusAbnormality = MutableLiveData<String>()
    val statusAbnormality: LiveData<String> get() = _statusAbnormality
    fun setStatusAbnormality(value : String) = _statusAbnormality.postValue(value)

    private val _nature = MutableLiveData<String>()
    val nature: LiveData<String> get() = _nature
    fun setNature(value : String) = _nature.postValue(value)

    val startSelectPokemon = SingleLiveEvent<Any>()
    fun onSelectPokemon() = startSelectPokemon.call()

    val startSetSpinner = SingleLiveEvent<Any>()
    fun onSetSpinner() {
        startSetSpinner.call()
    }

    val startComplete = SingleLiveEvent<Any>()
    fun onComplete() = startComplete.call()

    fun makePokemon() : Pokemon{
        return Pokemon(
            pokemonInfo.value!!,
            level.value!!.toInt(),
            AbilityInfo(0, ability.value!!, ""),
            ItemInfo(0, "아이템", "", false),
            listOf(MoveInfo(0, "기술1", 100, "불꽃", "특수", 100, "", false),
                MoveInfo(0, "기술2", 120, "풀", "물리", 100, "", false),
                MoveInfo(0, "기술3", 110, "물", "물리", 100, "", false),
                MoveInfo(0, "기술4", 80, "전기", "특수", 100, "", false)),
            Nature.NULL,
            EffortValues(evH.value!!.toInt(), evA.value!!.toInt(), evB.value!!.toInt(), evC.value!!.toInt(), evD.value!!.toInt(), evS.value!!.toInt()),
            IndividualValues(ivH.value!!.toInt(), ivA.value!!.toInt(), ivB.value!!.toInt(), ivC.value!!.toInt(), ivD.value!!.toInt(), ivS.value!!.toInt()),
            RankStates(rankA.value!!.toInt(), rankB.value!!.toInt(), rankC.value!!.toInt(), rankD.value!!.toInt(), rankS.value!!.toInt(),
                rankAcc.value!!.toInt(), rankEva.value!!.toInt(), rankCri.value!!.toInt()),
            StatusAbnormalityUtil.getStatusAbnormalityId(statusAbnormality.value!!),
            hp.value!!
        )
    }
}