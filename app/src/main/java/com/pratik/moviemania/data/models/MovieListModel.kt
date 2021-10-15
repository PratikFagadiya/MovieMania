package com.pratik.moviemania.data.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MovieListModel(
    val page: Int,

    val results: List<MovieListResult>,
    @SerializedName("total_pages")

    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)