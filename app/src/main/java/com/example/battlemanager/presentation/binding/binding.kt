package com.example.battlemanager.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.battlemanager.global.util.GlideUtil

@BindingAdapter("app:imageUrl")
fun bindImageUrl(view: ImageView, imageUrl: String?) {
    GlideUtil.setImage(imageUrl, view)
}