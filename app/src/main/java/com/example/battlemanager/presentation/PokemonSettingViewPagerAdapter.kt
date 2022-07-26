package com.example.battlemanager.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PokemonSettingViewPagerAdapter(f: Fragment, val viewModel : MainViewModel) : FragmentStateAdapter(f) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> if(viewModel.pokemon1.value != null) PokemonSettingFragment() else Fragment()
            1 -> if(viewModel.pokemon2.value != null) PokemonSettingFragment() else Fragment()
            else -> throw Exception("FragmentStateAdapter, 잘못된 position 입니다.")
        }
    }
}