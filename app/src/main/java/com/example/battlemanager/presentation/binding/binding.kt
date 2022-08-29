package com.example.battlemanager.presentation.binding

import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.battlemanager.R
import com.example.battlemanager.presentation.global.util.TypeUtil
import java.io.FileNotFoundException
import java.io.InputStream


@BindingAdapter("app:urlInAssets")
fun bindUrlInAssets(view: ImageView, imageUrl: String?) {
    if(imageUrl == null) {
        view.setImageResource(R.drawable.ic_launcher_foreground)
        return
    }

    try {
        val assetManager: AssetManager = view.context.resources.assets
        val inputStream: InputStream = assetManager.open(imageUrl)
        view.setImageDrawable(Drawable.createFromStream(inputStream, null))
        inputStream.close()
    }catch (e : FileNotFoundException){
        view.setImageResource(R.drawable.ic_launcher_foreground)
        return
    }
}

@BindingAdapter("app:typeBackground")
fun bindTypeBackground(view: View, type: String?){
    if(type == null)
        return
    view.setBackgroundResource(TypeUtil.getTypeBackground(type))
}