package com.example.battlemanager.presentation.main

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.battlemanager.R
import com.example.battlemanager.databinding.FragmentMainBinding
import com.example.battlemanager.presentation.global.base.BaseFragment
import com.example.battlemanager.presentation.PokemonMemory
import com.example.battlemanager.presentation.global.util.FieldUtil
import com.example.battlemanager.presentation.global.util.RealStatUtil
import com.example.battlemanager.presentation.global.util.StatusAbnormalityUtil
import com.example.battlemanager.presentation.pokemonSetting.PokemonSettingViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val layoutResourceId = R.layout.fragment_main
    private val viewModel: MainViewModel by viewModels({ requireParentFragment() })

    override fun initState() {
        super.initState()
        setPokemon()
        setSpinner()
    }

    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel
        viewModel.startPokemon1.observe(this) {
            val bundle = bundleOf("pos" to 1)
            findNavController().navigate(R.id.action_mainFragment_to_pokemonSettingFragment, bundle)
        }
        viewModel.startPokemon2.observe(this) {
            val bundle = bundleOf("pos" to 2)
            findNavController().navigate(R.id.action_mainFragment_to_pokemonSettingFragment, bundle)
        }
        viewModel.startResult.observe(this) {
            if (PokemonMemory.getIsValid(1) && PokemonMemory.getIsValid(2))
                findNavController().navigate(R.id.action_mainFragment_to_ResultFragment)
        }
        viewModel.startSwap.observe(this) {
            PokemonMemory.swapPokemon()
            setPokemon()
        }
        viewModel.weather.observe(this){
            if(PokemonMemory.getIsValid(1) && PokemonMemory.getIsValid(2))
                viewModel.setPreemptivePokemon()
        }
    }

    private fun setPokemon() {
        if (PokemonMemory.getIsValid(1)) {
            viewModel.setPokemon(PokemonMemory.getPokemon(1), 1)
        }
        if (PokemonMemory.getIsValid(2)) {
            viewModel.setPokemon(PokemonMemory.getPokemon(2), 2)
        }
        if(PokemonMemory.getIsValid(1) && PokemonMemory.getIsValid(2))
            viewModel.setPreemptivePokemon()
    }

    private fun setSpinner() {
        val weatherList = FieldUtil.getWeatherList()
        val weatherAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            weatherList
        )
        weatherAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.weatherSpinner.adapter = weatherAdapter
        binding.weatherSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setWeather(weatherList[position])
                    return
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.setWeather(weatherList[0])
                }

            }

        val fieldList = FieldUtil.getFieldList()
        val fieldAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            fieldList
        )
        fieldAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.fieldSpinner.adapter = fieldAdapter
        binding.fieldSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setField(fieldList[position])
                    return
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.setField(fieldList[0])
                }

            }
    }
}