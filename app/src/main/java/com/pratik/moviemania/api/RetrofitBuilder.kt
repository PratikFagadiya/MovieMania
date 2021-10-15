package com.pratik.moviemania.api

import com.pratik.moviemania.data.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    const val BASE_URL: String = "https://api.themoviedb.org/"
    const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w500"
    const val ORIGINAL_BASE_POSTER_PATH =
        "https://image.tmdb.org/t/p/original"   //https://image.tmdb.org/t/p/original/hpMCifxtUq65G3JbCUZE3bp6YxC.jpg

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}