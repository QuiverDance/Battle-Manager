package com.example.battlemanager.presentation.binding

import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.BindingAdapter
import com.example.battlemanager.R
import com.example.battlemanager.presentation.global.util.GenderUtil
import com.example.battlemanager.presentation.global.util.TypeUtil
import java.io.FileNotFoundException
import java.io.InputStream


@BindingAdapter("app:items")
fun bindItems(spinner: Spinner, items: List<String>?) {
    if(items == null)
        return
    val adapter = ArrayAdapter(
        spinner.context,
        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
        items
    )
    adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
    spinner.adapter = adapter
}