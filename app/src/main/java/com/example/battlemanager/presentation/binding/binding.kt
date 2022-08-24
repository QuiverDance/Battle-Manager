package com.example.battlemanager.presentation.binding

import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.battlemanager.R
import java.io.InputStream


@BindingAdapter("app:urlInAssets")
fun bindUrlInAssets(view: ImageView, imageUrl: String?) {
    if(imageUrl == null) {
        view.setImageResource(R.drawable.ic_launcher_foreground)
        return
    }

    val assetManager: AssetManager = view.context.resources.assets
    val inputStream: InputStream = assetManager.open(imageUrl)
    view.setImageDrawable(Drawable.createFromStream(inputStream, null))
    inputStream.close()
}