package com.example.battlemanager.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.battlemanager.R
import com.example.battlemanager.databinding.ActivityMainBinding
import com.example.battlemanager.databinding.FragmentMainBinding
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.global.base.BaseActivity
import com.example.battlemanager.global.base.BaseFragment
import com.google.android.material.tabs.TabLayout

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val layoutResourceId = R.layout.fragment_main
    private val viewModel: MainViewModel by viewModels()

    override fun initState() {
        super.initState()
    }

    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel
        setViewPager()
        viewModel.startPokemon1.observe(this){
            setDialog()
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

    private fun setDialog(){
        val pokemons = listOf(FilterItem(1, "", "1.이상해씨"), FilterItem(2, "", "2.이상해풀"), FilterItem(3, "", "3.이상해꽃"), 
            FilterItem(4, "", "4.파이리"), FilterItem(5, "", "5.리자드"), FilterItem(6, "", "6.리자몽"),
            FilterItem(7, "", "7.꼬부기"), FilterItem(8, "", "8.어니부기"), FilterItem(9, "", "9.거북왕"))
        val pokemon1Dialog = FilterDialogFragment(pokemons) {
            viewModel.setPokemon1(it)
        }
        pokemon1Dialog.show(childFragmentManager, "pokemon1")
    }
}