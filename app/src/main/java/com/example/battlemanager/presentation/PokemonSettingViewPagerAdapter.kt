package com.example.battlemanager.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PokemonSettingViewPagerAdapter(f: Fragment) : FragmentStateAdapter(f) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PokemonSettingFragment()
            1 -> PokemonSettingFragment()
            else -> throw Exception("FragmentStateAdapter, 잘못된 position 입니다.")
        }
    }
}