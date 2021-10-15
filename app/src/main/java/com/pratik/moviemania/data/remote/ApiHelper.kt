package com.pratik.moviemania.data.remote

class ApiHelper(private val apiService: ApiService) {

    suspend fun getPopularMovieList() = apiService.getPopularMovieList()

    suspend fun getTrendingMovieList() = apiService.getTrendingMovieList()

    suspend fun getTopRatedMovieList() = apiService.getTopRatedMovieList()

    suspend fun getUpcomingMovieList() = apiService.getUpcomingMovieList()

    suspend fun getFullMovieDetail(movieId: Int) = apiService.getFullMovieDetail(movieId)

}