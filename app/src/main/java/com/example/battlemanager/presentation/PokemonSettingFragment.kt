package com.example.battlemanager.presentation

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.battlemanager.R
import com.example.battlemanager.databinding.FragmentPokemonSettingBinding
import com.example.battlemanager.domain.model.Pokemon
import com.example.battlemanager.global.base.BaseFragment
import com.example.battlemanager.global.util.GenderUtil
import com.example.battlemanager.global.util.StatUtil
import com.example.battlemanager.global.util.StatusAbnormalityUtil

class PokemonSettingFragment : BaseFragment<FragmentPokemonSettingBinding>() {
    override val layoutResourceId = R.layout.fragment_pokemon_setting
    val viewModel: PokemonSettingViewModel by viewModels()

    var position = -1
    override fun initState() {
        super.initState()
        val pos = arguments?.getInt("pos") ?: -1
        position = pos
    }
    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel
        viewModel.getPokemonNameList()

        viewModel.startSelectPokemon.observe(this) {
            val pokemon1Dialog = FilterDialogFragment(viewModel.pokemonNameList.value!!) {
                viewModel.getPokemonInfo(it.id)
            }
            pokemon1Dialog.show(childFragmentManager, "pokemon")
        }
        viewModel.startSetSpinner.observe(this) {
            setSpinner()
            setHp()
        }
        viewModel.startComplete.observe(this) {
            if(position != -1)
                PokemonMemory.setPokemon(position, viewModel.makePokemon())
            findNavController().navigateUp()
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

        val abilityList = viewModel.pokemonInfo.value!!.validAbilityList
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
            abilityList
        )
        statusAbnormalityAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
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
    }

    fun setHp() {
        //레벨, H종족값, 노력치, 개체값에 의해 최대 값 변경
        val initHp = StatUtil.getHp(
            viewModel.pokemonInfo.value!!.name,
            50,
            viewModel.pokemonInfo.value!!.baseStats,
            0,
            0
        )
        binding.hpSeekBar.max = initHp
        viewModel.hp.value = initHp
        viewModel.hp_text.value = initHp.toString()

        viewModel.hp.observe(this) {
            viewModel.hp_text.value = it.toString()
        }

        viewModel.level.observe(this) {
            if(it != "") {
                val maxHp = StatUtil.getHp(
                    viewModel.pokemonInfo.value!!.name,
                    it.toInt(),
                    viewModel.pokemonInfo.value!!.baseStats,
                    viewModel.ivH.value!!.toInt(),
                    viewModel.evH.value!!.toInt()
                )
                binding.hpSeekBar.max = maxHp
            }
        }
        viewModel.pokemonInfo.observe(this) {
            val maxHp = StatUtil.getHp(
                it.name, viewModel.level.value!!.toInt(),
                it.baseStats,
                viewModel.ivH.value!!.toInt(),
                viewModel.evH.value!!.toInt()
            )
            binding.hpSeekBar.max = maxHp
        }
        viewModel.ivH.observe(this) {
            if (it != "") {
                val maxHp = StatUtil.getHp(
                    viewModel.pokemonInfo.value!!.name,
                    viewModel.level.value!!.toInt(),
                    viewModel.pokemonInfo.value!!.baseStats,
                    it.toInt(),
                    viewModel.evH.value!!.toInt()
                )
                binding.hpSeekBar.max = maxHp
            }
        }
        viewModel.evH.observe(this) {
            if (it != "") {
                val maxHp = StatUtil.getHp(
                    viewModel.pokemonInfo.value!!.name,
                    viewModel.level.value!!.toInt(),
                    viewModel.pokemonInfo.value!!.baseStats,
                    viewModel.ivH.value!!.toInt(),
                    it.toInt()
                )
                binding.hpSeekBar.max = maxHp
            }
        }
    }
}