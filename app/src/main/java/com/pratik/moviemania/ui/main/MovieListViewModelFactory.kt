package com.pratik.moviemania.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pratik.moviemania.data.MovieListRepository
import com.pratik.moviemania.data.remote.ApiHelper

class MovieListViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(MovieListRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}