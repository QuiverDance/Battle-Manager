package com.example.battlemanager.presentation.dialog.pokemonInfo

import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.battlemanager.R
import com.example.battlemanager.databinding.DialogInformationBinding
import com.example.battlemanager.databinding.DialogSearchBinding
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.domain.model.Pokemon
import com.example.battlemanager.presentation.global.base.BaseBottomSheetFragment
import com.example.battlemanager.presentation.global.base.BaseDialogFragment

class InfoDialogFragment(
    val pokemon: Pokemon,
) : BaseDialogFragment<DialogInformationBinding>() {
    override val layoutResourceId = R.layout.dialog_information
    private val viewModel: InfoViewModel by viewModels()

    override fun initState() {
        super.initState()
        viewModel.setPokemon(pokemon)
    }
    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel
    }
}