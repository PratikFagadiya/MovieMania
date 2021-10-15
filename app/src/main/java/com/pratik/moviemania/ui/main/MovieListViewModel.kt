package com.pratik.moviemania.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pratik.moviemania.data.MovieListRepository
import com.pratik.moviemania.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MovieListViewModel(private val movieListRepository: MovieListRepository) : ViewModel() {

    val listType: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getPopularMovieList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieListRepository.getPopularMovieList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getTrendingMovieList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieListRepository.getTrendingMovieList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getTopRatedMovieList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieListRepository.getTopRatedMovieList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getUpcomingMovieList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieListRepository.getUpcomingMovieList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}