package com.example.battlemanager.presentation.dialog.filter

import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.battlemanager.R
import com.example.battlemanager.databinding.DialogSearchBinding
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.presentation.global.base.BaseBottomSheetFragment

class FilterDialogFragment(
    val items: List<FilterItem>,
    val categories: List<String>,
    val selectedItem: (FilterItem) -> Unit
) : BaseBottomSheetFragment<DialogSearchBinding>() {
    override val layoutResourceId = R.layout.dialog_search
    private val viewModel: FilterViewModel by viewModels()

    override fun initDataBinding() {
        super.initDataBinding()
        binding.viewModel = viewModel

        setCategoryAdapter()

        viewModel.selectedCategory.observe(this) {
            val newItemList =
                if (viewModel.selectedCategory.value!! == 0) items
                else viewModel.getFilterItemByCategory(
                    items,
                    categories[it!!]
                )
            setSearchView(newItemList)
            setAdapter(newItemList)
        }
    }

    private fun setCategoryAdapter() {
        val adapter = CategoryAdapter(viewModel.getCategoryList(categories))
        adapter.setOnCategoryItemClickListener(object :
            CategoryAdapter.OnCategoryItemClickListener {
            override fun onItemClick(view: View, pos: Int) {
                viewModel.selectCategory(pos)
            }
        })
        binding.categoryRecycler.adapter = adapter
    }

    private fun setSearchView(itemList: List<FilterItem>) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null)
                    return false
                val newItems = mutableListOf<FilterItem>()
                for (item in itemList) {
                    if (item.itemName.contains(newText)) {
                        newItems.add(item)
                    }
                }
                setAdapter(newItems)
                return false
            }
        })
    }

    private fun setAdapter(dataset: List<FilterItem>) {
        val adapter = FilterAdapter(dataset)
        adapter.setOnFilterItemClickListener(object : FilterAdapter.OnFilterItemClickListener {
            override fun onItemClick(view: View, pos: Int) {
                selectedItem(adapter.getItem(pos))
                dismiss()
            }
        })
        binding.filterRecycler.adapter = adapter
    }
}