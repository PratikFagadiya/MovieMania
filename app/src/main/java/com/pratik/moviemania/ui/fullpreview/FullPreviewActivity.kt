package com.pratik.moviemania.ui.fullpreview

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.pratik.moviemania.R
import com.pratik.moviemania.api.RetrofitBuilder
import com.pratik.moviemania.data.remote.ApiHelper
import com.pratik.moviemania.databinding.ActivityFullPreviewBinding
import com.pratik.moviemania.ui.base.BaseActivity
import com.pratik.moviemania.utils.Status

class FullPreviewActivity : BaseActivity() {

    lateinit var activityFullPreviewBinding: ActivityFullPreviewBinding
    lateinit var fullPreviewViewModel: FullPreviewViewModel

    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFullPreviewBinding = setView(this, R.layout.activity_full_preview)

        movieId = intent.getIntExtra("movieId", 0)

        initView(movieId)
    }

    private fun initView(movieId: Int) {
        setupViewModel()
        setUpObserver(movieId)
    }

    private fun setupViewModel() {
        fullPreviewViewModel = ViewModelProviders.of(
            this,
            FullPreviewViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(FullPreviewViewModel::class.java)
    }

    private fun setUpObserver(movieId: Int) {
        fullPreviewViewModel.getFullMovieDetail(movieId).observe(this,
            { fullPreviewResponse ->

                fullPreviewResponse?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Log.i("FULLPREVIEW", "setUpObserver: Success")
                        }

                        Status.ERROR -> {
                            Log.i("FULLPREVIEW", "setUpObserver: Error  ")
                        }

                        Status.LOADING -> {
                            Log.i("FULLPREVIEW", "setUpObserver: Loading")
                        }
                    }
                }
            })
    }
}