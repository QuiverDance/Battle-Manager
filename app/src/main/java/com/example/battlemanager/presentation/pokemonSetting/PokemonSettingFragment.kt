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
import com.example.battlemanager.presentation.global.constant.Stat
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

        viewModel.rightMenu1.observe(this){
            if(position != -1)
                PokemonMemory.setPokemon(position, viewModel.makePokemon())
            findNavController().navigateUp()
        }
        viewModel.leftMenu.observe(this){
            findNavController().navigateUp()
        }
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
        viewModel.setGenderList()
        binding.genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.setGender(GenderUtil.getGenderId(viewModel.genderList.value!![position]))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.setGender(GenderUtil.getGenderId(viewModel.genderList.value!![0]))
            }

        }

        viewModel.setAbilityList()
        binding.abilitySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setAbility(viewModel.abilityList.value!![position])
                    return
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.setAbility(viewModel.abilityList.value!![0])
                }

            }

        viewModel.setStatusAbnormalityList()
        binding.statusAbnormalitySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setStatusAbnormality(viewModel.statusAbnormalityList.value!![position])
                    return
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.setStatusAbnormality(viewModel.statusAbnormalityList.value!![0])
                }

            }

        viewModel.setNatureList()
        binding.natureSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setNature(viewModel.natureList.value!![position])
                    return
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.setNature(viewModel.natureList.value!![0])
                }

            }
    }

    private fun setHp() {
        //레벨, H종족값, 노력치, 개체값에 의해 최대 값 변경
        val initHp = StatUtil.getHp(
            viewModel.pokemonInfo.value!!.name,
            50,
            viewModel.pokemonInfo.value!!.baseStats[Stat.HP],
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
                    viewModel.pokemonInfo.value!!.baseStats[Stat.HP],
                    viewModel.ivList[0].value!!.toInt(),
                    viewModel.evList[0].value!!.toInt()
                )
                binding.hpSeekBar.max = maxHp
            }
            catch (e : NumberFormatException){}
        }
        viewModel.pokemonInfo.observe(this) {
            try {
                val maxHp = StatUtil.getHp(
                    it.name, viewModel.level.value!!.toInt(),
                    it.baseStats[Stat.HP],
                    viewModel.ivList[0].value!!.toInt(),
                    viewModel.evList[0].value!!.toInt()
                )
                binding.hpSeekBar.max = maxHp
            }
            catch (e : NumberFormatException){}
        }
        viewModel.ivList[0].observe(this) {
            try{
                val maxHp = StatUtil.getHp(
                    viewModel.pokemonInfo.value!!.name,
                    viewModel.level.value!!.toInt(),
                    viewModel.pokemonInfo.value!!.baseStats[Stat.HP],
                    it.toInt(),
                    viewModel.evList[0].value!!.toInt()
                )
                binding.hpSeekBar.max = maxHp
            }
            catch (e : NumberFormatException){}
        }
        viewModel.evList[0].observe(this) {
            try {
                val maxHp = StatUtil.getHp(
                    viewModel.pokemonInfo.value!!.name,
                    viewModel.level.value!!.toInt(),
                    viewModel.pokemonInfo.value!!.baseStats[Stat.HP],
                    viewModel.ivList[0].value!!.toInt(),
                    it.toInt()
                )
                binding.hpSeekBar.max = maxHp
            }
            catch(e : NumberFormatException){}
        }
    }
}