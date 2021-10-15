package com.pratik.moviemania.data.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AuthorDetails(
    @SerializedName("avatar_path")
    val avatarPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("username")
    val username: String
)