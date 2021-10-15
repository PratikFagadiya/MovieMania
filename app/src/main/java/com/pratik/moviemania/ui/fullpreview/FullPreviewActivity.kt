package com.pratik.moviemania.ui.fullpreview

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.pratik.moviemania.R
import com.pratik.moviemania.api.RetrofitBuilder
import com.pratik.moviemania.data.remote.ApiHelper
import com.pratik.moviemania.databinding.ActivityFullPreviewBinding
import com.pratik.moviemania.ui.base.BaseActivity
import com.pratik.moviemania.ui.binding.bindPosterPathWithGlide
import com.pratik.moviemania.utils.Status

class FullPreviewActivity : BaseActivity() {

    lateinit var activityFullPreviewBinding: ActivityFullPreviewBinding
    lateinit var fullPreviewViewModel: FullPreviewViewModel
    lateinit var imagePreview: ImageView
    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFullPreviewBinding = setView(this, R.layout.activity_full_preview)

        movieId = intent.getIntExtra("movieId", 0)
        imagePreview = findViewById(R.id.imgMainPoster)
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
                            Log.d(
                                "FULLPREVIEW",
                                "setUpObserver: https://image.tmdb.org/t/p/w500" + resource.data?.images?.posters?.first()?.filePath
                            )
                            val url =
                                "https://image.tmdb.org/t/p/w500" + resource.data?.images?.posters?.first()?.filePath
                            Glide.with(this.context).load(url)
                                .transform(CenterCrop(), RoundedCorners(5)).into(imagePreview)
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