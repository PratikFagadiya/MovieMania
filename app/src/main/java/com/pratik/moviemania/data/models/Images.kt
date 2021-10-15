package com.pratik.moviemania.data.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Images(
    @SerializedName("backdrops")
    val backdrops: List<Backdrop>,
    @SerializedName("logos")
    val logos: List<Logo>,
    @SerializedName("posters")
    val posters: List<Poster>
)