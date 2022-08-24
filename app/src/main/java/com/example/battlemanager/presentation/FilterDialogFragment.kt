package com.example.battlemanager.presentation

import android.view.View
import android.widget.SearchView
import com.example.battlemanager.R
import com.example.battlemanager.databinding.DialogSearchBinding
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.presentation.global.base.BaseDialogFragment

class FilterDialogFragment(val items: List<FilterItem>, val selectedItem: (FilterItem) -> Unit) : BaseDialogFragment<DialogSearchBinding>() {
    override val layoutResourceId = R.layout.dialog_search

//    val selectedItem = ArrayList<FilterItem>()
    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@FilterDialogFragment, 0.8f, 0.8f)
    }

    override fun initDataBinding() {
        super.initDataBinding()
        setSearchView()
        setAdapter(items)
    }
    private fun setSearchView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText == null)
                    return false
                val newItems = mutableListOf<FilterItem>()
                for(item in items){
                    if(item.itemName.contains(newText)){
                        newItems.add(item)
                    }
                }
                setAdapter(newItems)
                return false
            }
        })
    }
    private fun setAdapter(dataset: List<FilterItem>){
        val adapter = FilterAdapter(dataset)
        adapter.setOnFilterItemClickListener(object : FilterAdapter.OnFilterItemClickListener {
            override fun onItemClick(view: View, pos: Int) {
                //selectedItem.add(adapter.getItem(pos));
                selectedItem(adapter.getItem(pos))
                dismiss()
            }
        })
        binding.filterRecycler.adapter = adapter
    }
}