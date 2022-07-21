package com.example.battlemanager.presentation

import androidx.fragment.app.viewModels
import com.example.battlemanager.R
import com.example.battlemanager.databinding.FragmentMainBinding
import com.example.battlemanager.global.base.BaseFragment
import com.google.android.material.tabs.TabLayout

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val layoutResourceId = R.layout.fragment_main
    private val viewModel: MainViewModel by viewModels()

    override fun initState() {
        super.initState()
        viewModel.getPokemonNameList()
    }

    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel
        setViewPager()
        viewModel.startPokemon1.observe(this){
            val pokemon1Dialog = FilterDialogFragment(viewModel.pokemonNameList.value!!) {
                viewModel.getPokemon1(it.id)
            }
            pokemon1Dialog.show(childFragmentManager, "pokemon")
        }
        viewModel.startPokemon2.observe(this){
            val pokemon1Dialog = FilterDialogFragment(viewModel.pokemonNameList.value!!) {
                viewModel.getPokemon2(it.id)
            }
            pokemon1Dialog.show(childFragmentManager, "pokemon")
        }
    }

    private fun setViewPager(){
        with(binding.pokemonViewPager) {
            adapter = PokemonSettingViewPagerAdapter(this@MainFragment)
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