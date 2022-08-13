package com.example.battlemanager.presentation

import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.battlemanager.R
import com.example.battlemanager.databinding.FragmentBattleResultBinding
import com.example.battlemanager.global.base.BaseFragment
import com.example.battlemanager.global.util.DamageUtil
import com.example.battlemanager.global.util.StatUtil
import kotlin.math.ceil

class BattleResultFragment : BaseFragment<FragmentBattleResultBinding>() {
    override val layoutResourceId = R.layout.fragment_battle_result
    private val viewModel: MainViewModel by viewModels({ requireParentFragment() })

    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel
        //setMaxHp()
        setHp(binding.move1MinHpProgressbar, 0, false, 0, binding.move1MinHpText, binding.move1MinMaxhpText)
        setHp(binding.move1MaxHpProgressbar, 0, false, 1, binding.move1MaxHpText, binding.move1MaxMaxhpText)
        setHp(binding.move1CriticalMinHpProgressbar, 0, true, 0, binding.move1CriticalMinHpText, binding.move1CriticalMinMaxhpText)
        setHp(binding.move1CriticalMaxHpProgressbar, 0, true, 1, binding.move1CriticalMaxHpText, binding.move1CriticalMaxMaxhpText)

        setHp(binding.move2MinHpProgressbar, 1, false, 0, binding.move2MinHpText, binding.move2MinMaxhpText)
        setHp(binding.move2MaxHpProgressbar, 1, false, 1, binding.move2MaxHpText, binding.move2MaxMaxhpText)
        setHp(binding.move2CriticalMinHpProgressbar, 1, true, 0, binding.move2CriticalMinHpText, binding.move2CriticalMinMaxhpText)
        setHp(binding.move2CriticalMaxHpProgressbar, 1, true, 1, binding.move2CriticalMaxHpText, binding.move2CriticalMaxMaxhpText)

        setHp(binding.move3MinHpProgressbar, 2, false, 0, binding.move3MinHpText, binding.move3MinMaxhpText)
        setHp(binding.move3MaxHpProgressbar, 2, false, 1, binding.move3MaxHpText, binding.move3MaxMaxhpText)
        setHp(binding.move3CriticalMinHpProgressbar, 2, true, 0, binding.move3CriticalMinHpText, binding.move3CriticalMinMaxhpText)
        setHp(binding.move3CriticalMaxHpProgressbar, 2, true, 1, binding.move3CriticalMaxHpText, binding.move3CriticalMaxMaxhpText)

        setHp(binding.move4MinHpProgressbar, 3, false, 0, binding.move4MinHpText, binding.move4MinMaxhpText)
        setHp(binding.move4MaxHpProgressbar, 3, false, 1, binding.move4MaxHpText, binding.move4MaxMaxhpText)
        setHp(binding.move4CriticalMinHpProgressbar, 3, true, 0, binding.move4CriticalMinHpText, binding.move4CriticalMinMaxhpText)
        setHp(binding.move4CriticalMaxHpProgressbar, 3, true, 1, binding.move4CriticalMaxHpText, binding.move4CriticalMaxMaxhpText)
    }

    private fun setHp(progressBar: ProgressBar, moveIdx: Int, isCritical: Boolean, randType: Int, curTextView: TextView, maxTextView: TextView) {
        val damage = DamageUtil.getDamage(
            viewModel.pokemon1.value!!,
            viewModel.pokemon2.value!!,
            viewModel.pokemon1.value!!.moves[moveIdx],
            "없음",
            isCritical,
            randType
        )
        val result = viewModel.pokemon2.value!!.hp - damage
        val progress = ceil(result.toDouble() / viewModel.pokemon2.value!!.maxHp * 100).toInt()

        if(result < 0) progressBar.progress = 0
        else progressBar.progress = progress
        curTextView.text = result.toString()
        maxTextView.text = viewModel.pokemon2.value!!.maxHp.toString()
    }
}