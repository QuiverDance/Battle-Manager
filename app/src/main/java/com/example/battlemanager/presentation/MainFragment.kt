package com.example.battlemanager.presentation

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.battlemanager.R
import com.example.battlemanager.databinding.FragmentMainBinding
import com.example.battlemanager.domain.model.Pokemon
import com.example.battlemanager.global.base.BaseFragment
import com.google.android.material.tabs.TabLayout

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val layoutResourceId = R.layout.fragment_main
    private val viewModel: MainViewModel by viewModels({ requireParentFragment() })

    override fun initState() {
        super.initState()
        if(PokemonMemory.getIsValid(1)){
            viewModel.setPokemon(PokemonMemory.getPokemon(1), 1)
        }
        if(PokemonMemory.getIsValid(2)){
            viewModel.setPokemon(PokemonMemory.getPokemon(2), 2)
        }
    }

    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel
        //setViewPager()
        viewModel.startPokemon1.observe(this){
//            val pokemon1Dialog = FilterDialogFragment(viewModel.pokemonNameList.value!!) {
//                viewModel.getPokemonInfo(it.id, 1)
//            }
//            pokemon1Dialog.show(childFragmentManager, "pokemon")
            val bundle = bundleOf("pos" to 1)
            findNavController().navigate(R.id.action_mainFragment_to_pokemonSettingFragment, bundle)
        }
        viewModel.startPokemon2.observe(this){
//            val pokemon1Dialog = FilterDialogFragment(viewModel.pokemonNameList.value!!) {
//                viewModel.getPokemonInfo(it.id, 2)
//            }
            val bundle = bundleOf("pos" to 2)
            findNavController().navigate(R.id.action_mainFragment_to_pokemonSettingFragment, bundle)
        }
        viewModel.startResult.observe(this){
            if(PokemonMemory.getIsValid(1) && PokemonMemory.getIsValid(2))
                findNavController().navigate(R.id.action_mainFragment_to_ResultFragment)
        }
    }

    private fun setViewPager(){
        with(binding.pokemonViewPager) {
            adapter = PokemonSettingViewPagerAdapter(this@MainFragment, viewModel)
            isUserInputEnabled = false
            binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val position = tab!!.position
                    currentItem = position
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }
}