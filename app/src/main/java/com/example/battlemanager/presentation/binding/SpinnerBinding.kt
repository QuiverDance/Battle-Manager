package com.example.battlemanager.presentation.binding

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

object InverseSpinnerBindings {

    @JvmStatic
    @BindingAdapter("selectedValue", "items")
    fun Spinner.setSelectedValue(selectedValue: Any?, items: List<String>?) {
        if(items == null || selectedValue == null)
            return
        if(selectedItem != selectedValue) {
            tag = items.indexOf(selectedValue)
            setSelection(tag as Int)
        }
    }

    @JvmStatic
    @BindingAdapter("selectedValueAttrChanged")
    fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
        inverseBindingListener?.run {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (tag != position) {
                        inverseBindingListener.onChange()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    fun Spinner.getSelectedValue(): Any? {
        return selectedItem
    }
}