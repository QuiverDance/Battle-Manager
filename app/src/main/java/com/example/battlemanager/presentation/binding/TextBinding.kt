package com.example.battlemanager.presentation.binding

import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.battlemanager.R
import com.example.battlemanager.presentation.global.util.TypeUtil
import java.io.FileNotFoundException
import java.io.InputStream


@BindingAdapter("app:intText")
fun bindIntText(view: TextView, value: Int?) {
    if(value == null)
        return
    view.text = value.toString()
}