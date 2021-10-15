package com.pratik.moviemania.data.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class BelongsToCollection(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
)