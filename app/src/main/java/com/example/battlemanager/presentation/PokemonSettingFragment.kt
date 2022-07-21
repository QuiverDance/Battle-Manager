package com.example.battlemanager.presentation

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.battlemanager.R
import com.example.battlemanager.databinding.FragmentPokemonSettingBinding
import com.example.battlemanager.global.base.BaseFragment

class PokemonSettingFragment : BaseFragment<FragmentPokemonSettingBinding>() {
    override val layoutResourceId = R.layout.fragment_pokemon_setting
    override fun initDataBinding() {
        super.initDataBinding()
        setSpinner()
    }
    private fun setSpinner(){
        val genderAdapter = ArrayAdapter<String>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayOf("남", "여"))
        genderAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.genderSpinner.adapter = genderAdapter
        binding.genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                return
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val abilityAdapter = ArrayAdapter<String>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayOf("특성1", "특성2", "특성3"))
        abilityAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.abilitySpinner.adapter = abilityAdapter
        binding.abilitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                return
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
}