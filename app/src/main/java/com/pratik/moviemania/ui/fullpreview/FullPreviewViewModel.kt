package com.pratik.moviemania.ui.fullpreview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

import com.pratik.moviemania.data.MovieListRepository
import com.pratik.moviemania.utils.Resource
import kotlinx.coroutines.Dispatchers

class FullPreviewViewModel(private val movieListRepository: MovieListRepository) : ViewModel() {

    fun getFullMovieDetail(movieId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieListRepository.getFullMovieDetail(movieId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}