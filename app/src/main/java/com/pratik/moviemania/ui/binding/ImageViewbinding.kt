package com.pratik.moviemania.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.pratik.moviemania.api.RetrofitBuilder.BASE_POSTER_PATH

@BindingAdapter("bind_poster_path")
fun ImageView.bindPosterPathWithGlide(path: String?) {
    if (path.isNullOrBlank()) {
        // TODO: 02-10-2021 Path is Null Or Blank
        return
    }
//    this.load(BASE_POSTER_PATH + path){
//        crossfade(true)
//    }
    Glide.with(this.context).load(BASE_POSTER_PATH + path)
        .transform(CenterCrop(), RoundedCorners(5)).into(this)
}