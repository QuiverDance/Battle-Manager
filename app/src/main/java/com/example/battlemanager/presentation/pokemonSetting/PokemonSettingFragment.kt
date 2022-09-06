package com.example.battlemanager.presentation.pokemonSetting

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.battlemanager.R
import com.example.battlemanager.databinding.FragmentPokemonSettingBinding
import com.example.battlemanager.presentation.global.base.BaseFragment
import com.example.battlemanager.presentation.dialog.filter.FilterDialogFragment
import com.example.battlemanager.presentation.PokemonMemory
import com.example.battlemanager.presentation.global.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonSettingFragment : BaseFragment<FragmentPokemonSettingBinding>() {
    override val layoutResourceId = R.layout.fragment_pokemon_setting
    val viewModel: PokemonSettingViewModel by viewModels()

    var position = -1
    override fun initState() {
        super.initState()
        val pos = arguments?.getInt("pos") ?: -1
        position = pos

        if(PokemonMemory.getIsValid(pos))
            viewModel.initPokemonInfo(PokemonMemory.getPokemon(pos))
    }
    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel

        viewModel.startSelectPokemon.observe(this) {
            viewModel.getPokemonFilterList()
        }
        viewModel.endGetPokemonList.observe(this){
            val pokemonDialog = FilterDialogFragment(viewModel.pokemonFilterList.value!!, TypeUtil.getTypeList()) {
                viewModel.getPokemonInfo(it.itemName)
            }
            pokemonDialog.show(childFragmentManager, "pokemon")
        }
        viewModel.startSetSpinner.observe(this) {
            setSpinner()
            setHp()
        }
        viewModel.rightMenu1.observe(this){
            if(position != -1)
                PokemonMemory.setPokemon(position, viewModel.makePokemon())
            findNavController().navigateUp()
        }
        viewModel.leftMenu.observe(this){
            findNavController().navigateUp()
        }
        viewModel.selectedMove.observe(this){
            if(viewModel.selectedMove.value!! >= 0)
                viewModel.getMoveFilterList()
        }
        viewModel.endGetMoveList.observe(this){
            val moveDialog = FilterDialogFragment(viewModel.moveFilterList.value!!, TypeUtil.getTypeList()) {
                viewModel.setMoveInfo(it.itemName, viewModel.selectedMove.value!!)
            }
            moveDialog.show(childFragmentManager, "move")
        }
        viewModel.startSelectItem.observe(this){
            viewModel.getItemFilterList()
        }
        viewModel.endGetItemList.observe(this){
            val itemDialog = FilterDialogFragment(viewModel.itemFilterList.value!!, ItemUtil.getItemCategory()) {
                viewModel.getItemInfo(it.itemName)
            }
            itemDialog.show(childFragmentManager, "item")
        }
    }

    private fun setSpinner() {
        val genderList = GenderUtil.getGenderList(viewModel.pokemonInfo.value!!.validGender)
        val genderAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            genderList
        )
        genderAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.genderSpinner.adapter = genderAdapter
        binding.genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.setGender(GenderUtil.getGenderId(genderList[position]))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.setGender(GenderUtil.getGenderId(genderList[0]))
            }

        }

        val abilityList = mutableListOf<String>()
        for(value in viewModel.pokemonInfo.value!!.validAbilityList){
            if(value != "")
                abilityList.add(value)
        }
        val abilityAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            abilityList
        )
        abilityAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.abilitySpinner.adapter = abilityAdapter
        binding.abilitySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setAbility(abilityList[position])
                    return
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.setAbility(abilityList[0])
                }

            }

        val statusAbnormalityList = StatusAbnormalityUtil.getStatusAbnormalityList()
        val statusAbnormalityAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            statusAbnormalityList
        )
        statusAbnormalityAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.statusAbnormalitySpinner.adapter = statusAbnormalityAdapter
        binding.statusAbnormalitySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setStatusAbnormality(statusAbnormalityList[position])
                    return
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.setStatusAbnormality(statusAbnormalityList[0])
                }

            }

        val natureList = NatureUtil.getNatureList()
        val natureAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            natureList
        )
        natureAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.natureSpinner.adapter = natureAdapter
        binding.natureSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setNature(natureList[position])
                    return
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.setNature(natureList[0])
                }

            }
    }

    private fun setHp() {
        //레벨, H종족값, 노력치, 개체값에 의해 최대 값 변경
        val initHp = StatUtil.getHp(
            viewModel.pokemonInfo.value!!.name,
            50,
            viewModel.pokemonInfo.value!!.baseStats.H,
            0,
            0
        )
        binding.hpSeekBar.max = initHp
        viewModel.hp.value = initHp
        viewModel.hpText.value = initHp.toString()

        viewModel.hp.observe(this) {
            viewModel.hpText.value = it.toString()
        }

        viewModel.level.observe(this) {
            try {
                val maxHp = StatUtil.getHp(
                    viewModel.pokemonInfo.value!!.name,
                    it.toInt(),
                    viewModel.pokemonInfo.value!!.baseStats.H,
                    viewModel.ivH.value!!.toInt(),
                    viewModel.evH.value!!.toInt()
                )
                binding.hpSeekBar.max = maxHp
            }
            catch (e : NumberFormatException){}
        }
        viewModel.pokemonInfo.observe(this) {
            try {
                val maxHp = StatUtil.getHp(
                    it.name, viewModel.level.value!!.toInt(),
                    it.baseStats.H,
                    viewModel.ivH.value!!.toInt(),
                    viewModel.evH.value!!.toInt()
                )
                binding.hpSeekBar.max = maxHp
            }
            catch (e : NumberFormatException){}
        }
        viewModel.ivH.observe(this) {
            try{
                val maxHp = StatUtil.getHp(
                    viewModel.pokemonInfo.value!!.name,
                    viewModel.level.value!!.toInt(),
                    viewModel.pokemonInfo.value!!.baseStats.H,
                    it.toInt(),
                    viewModel.evH.value!!.toInt()
                )
                binding.hpSeekBar.max = maxHp
            }
            catch (e : NumberFormatException){}
        }
        viewModel.evH.observe(this) {
            try {
                val maxHp = StatUtil.getHp(
                    viewModel.pokemonInfo.value!!.name,
                    viewModel.level.value!!.toInt(),
                    viewModel.pokemonInfo.value!!.baseStats.H,
                    viewModel.ivH.value!!.toInt(),
                    it.toInt()
                )
                binding.hpSeekBar.max = maxHp
            }
            catch(e : NumberFormatException){}
        }
    }
}