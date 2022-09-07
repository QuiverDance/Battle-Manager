package com.example.battlemanager.presentation.battleResult

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.battlemanager.R
import com.example.battlemanager.databinding.FragmentBattleResultBinding
import com.example.battlemanager.presentation.global.base.BaseFragment
import com.example.battlemanager.presentation.main.MainViewModel

class BattleResultFragment : BaseFragment<FragmentBattleResultBinding>() {
    override val layoutResourceId = R.layout.fragment_battle_result
    private val viewModel: MainViewModel by viewModels({ requireParentFragment() })

    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel

        viewModel.leftMenu.observe(this) {
            findNavController().navigateUp()
        }

        for (i in 0..3) {
            viewModel.setRemainingHpListByMove(i, 0, i, false, 0)
            viewModel.setRemainingHpListByMove(i, 1, i, false, 1)
            viewModel.setRemainingHpListByMove(i, 2, i, true, 0)
            viewModel.setRemainingHpListByMove(i, 3, i, true, 1)
        }
    }
}