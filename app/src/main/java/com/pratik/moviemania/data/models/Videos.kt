package com.pratik.moviemania.data.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Videos(
    @SerializedName("results")
    val results: List<ResultX>
)