package com.pratik.moviemania.ui.callbacks

import com.pratik.moviemania.data.models.MovieListResult

interface MovieListClickListener {
    fun movieListClick(movieDetail: MovieListResult)
}