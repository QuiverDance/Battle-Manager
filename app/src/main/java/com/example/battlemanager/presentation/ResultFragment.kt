package com.example.battlemanager.presentation

import androidx.fragment.app.viewModels
import com.example.battlemanager.R
import com.example.battlemanager.databinding.FragmentMainBinding
import com.example.battlemanager.global.base.BaseFragment

class ResultFragment : BaseFragment<FragmentMainBinding>() {
    override val layoutResourceId = R.layout.fragment_main
    private val viewModel: MainViewModel by viewModels({ requireParentFragment() })

    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel
    }
}