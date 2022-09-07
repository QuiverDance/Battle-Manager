package com.example.battlemanager.presentation.dialog.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.battlemanager.domain.model.CategoryItem
import com.example.battlemanager.domain.model.FilterItem
import com.example.battlemanager.presentation.global.util.SingleLiveEvent

class FilterViewModel : ViewModel() {
    private val _selectedCategory = MutableLiveData<Int>(0)
    val selectedCategory: LiveData<Int> get() = _selectedCategory
    fun selectCategory(pos: Int){
        _selectedCategory.postValue(pos)
    }

    fun getCategoryList(list: List<String>) : List<CategoryItem>{
        val categoryList = mutableListOf<CategoryItem>()
        for(value in list){
            categoryList.add(CategoryItem(value, false))
        }
        return categoryList
    }

    fun getFilterItemByCategory(list: List<FilterItem>, category: String): List<FilterItem>{
        val filterItemList = mutableListOf<FilterItem>()
        for(item in list){
            if(item.type == category)
                filterItemList.add(item)
        }
        return filterItemList
    }
}