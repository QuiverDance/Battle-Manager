package com.example.battlemanager.presentation.global.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.battlemanager.R

object GlideUtil {
    fun setImage(url: String?, view : ImageView){
        if(url == null) return

        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .into(view)
    }
}