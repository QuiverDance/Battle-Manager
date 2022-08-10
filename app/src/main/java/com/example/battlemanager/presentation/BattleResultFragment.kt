package com.example.battlemanager.presentation

import androidx.fragment.app.viewModels
import com.example.battlemanager.R
import com.example.battlemanager.databinding.FragmentBattleResultBinding
import com.example.battlemanager.global.base.BaseFragment
import com.example.battlemanager.global.util.StatUtil

class BattleResultFragment : BaseFragment<FragmentBattleResultBinding>() {
    override val layoutResourceId = R.layout.fragment_battle_result
    private val viewModel: MainViewModel by viewModels({ requireParentFragment() })

    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel
        setMaxHp()
    }
    private fun setMaxHp(){
        val pokemon = viewModel.pokemon2.value!!
        val maxHp = StatUtil.getHp(pokemon.pokemonInfo.name, pokemon.level, pokemon.pokemonInfo.baseStats.H, pokemon.individualValues.H, pokemon.effortValues.H)

        binding.move1MaxHpProgressbar.max = maxHp
        binding.move1MinHpProgressbar.max = maxHp
        binding.move2MaxHpProgressbar.max = maxHp
        binding.move2MinHpProgressbar.max = maxHp
        binding.move3MaxHpProgressbar.max = maxHp
        binding.move3MinHpProgressbar.max = maxHp
        binding.move4MaxHpProgressbar.max = maxHp
        binding.move4MinHpProgressbar.max = maxHp
    }
}