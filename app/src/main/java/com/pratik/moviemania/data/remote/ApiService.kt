package com.pratik.moviemania.data.remote

import com.pratik.moviemania.data.models.MovieDetail
import com.pratik.moviemania.data.models.MovieListModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("3/movie/popular?api_key=ea9a49ebf2b74721a75aae271ebd3036")
    suspend fun getPopularMovieList(): MovieListModel

    @GET("3/trending/all/day?api_key=ea9a49ebf2b74721a75aae271ebd3036")
    suspend fun getTrendingMovieList(): MovieListModel

    @GET("3/movie/top_rated?api_key=ea9a49ebf2b74721a75aae271ebd3036")
    suspend fun getTopRatedMovieList(): MovieListModel

    @GET("3/movie/upcoming?api_key=ea9a49ebf2b74721a75aae271ebd3036")
    suspend fun getUpcomingMovieList(): MovieListModel

    @GET("3/movie/{movieId}?api_key=ea9a49ebf2b74721a75aae271ebd3036&append_to_response=videos,images,reviews")
    suspend fun getFullMovieDetail(@Path("movieId") movieId: Int): MovieDetail

}