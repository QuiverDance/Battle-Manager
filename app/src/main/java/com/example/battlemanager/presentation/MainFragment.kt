package com.example.battlemanager.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.battlemanager.R
import com.example.battlemanager.databinding.ActivityMainBinding
import com.example.battlemanager.databinding.FragmentMainBinding
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
        setViewPager()
    }

    fun setViewPager(){
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

    fun setSpinner(){

    }
}