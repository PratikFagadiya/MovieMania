package com.pratik.moviemania.ui.fullpreview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pratik.moviemania.data.MovieListRepository
import com.pratik.moviemania.data.remote.ApiHelper

class FullPreviewViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FullPreviewViewModel::class.java)) {
            return FullPreviewViewModel(MovieListRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}