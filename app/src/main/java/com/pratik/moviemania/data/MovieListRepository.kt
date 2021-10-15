package com.pratik.moviemania.data

import com.pratik.moviemania.data.remote.ApiHelper

class MovieListRepository(private val apiHelper: ApiHelper) {

    suspend fun getPopularMovieList() = apiHelper.getPopularMovieList()

    suspend fun getTrendingMovieList() = apiHelper.getTrendingMovieList()

    suspend fun getTopRatedMovieList() = apiHelper.getTopRatedMovieList()

    suspend fun getUpcomingMovieList() = apiHelper.getUpcomingMovieList()

    suspend fun getFullMovieDetail(movieId: Int) = apiHelper.getFullMovieDetail(movieId)

}