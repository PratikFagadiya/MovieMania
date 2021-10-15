package com.pratik.moviemania.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratik.moviemania.R
import com.pratik.moviemania.api.RetrofitBuilder
import com.pratik.moviemania.data.models.MovieListResult
import com.pratik.moviemania.data.remote.ApiHelper
import com.pratik.moviemania.databinding.ActivityMainBinding
import com.pratik.moviemania.ui.adapters.HorizontalMovieListAdapter
import com.pratik.moviemania.ui.base.BaseActivity
import com.pratik.moviemania.ui.callbacks.MovieListClickListener
import com.pratik.moviemania.ui.fulllist.FullListActivity
import com.pratik.moviemania.ui.fullpreview.FullPreviewActivity
import com.pratik.moviemania.utils.Constants.POPULAR
import com.pratik.moviemania.utils.Constants.TOP_RATED
import com.pratik.moviemania.utils.Constants.TRENDING
import com.pratik.moviemania.utils.Constants.UPCOMING
import com.pratik.moviemania.utils.Status

class MainActivity : BaseActivity(), MovieListClickListener {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var movieListViewModel: MovieListViewModel
    private var popularMovieList = ArrayList<MovieListResult>()
    private var trendingMovieList = ArrayList<MovieListResult>()
    private var topRatedMovieList = ArrayList<MovieListResult>()
    private var upcomingMovieList = ArrayList<MovieListResult>()

    private lateinit var popularMovieListAdapter: HorizontalMovieListAdapter
    private lateinit var trendingMovieListAdapter: HorizontalMovieListAdapter
    private lateinit var topRatedMovieListAdapter: HorizontalMovieListAdapter
    private lateinit var upcomingMovieListAdapter: HorizontalMovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = setView(this, R.layout.activity_main)

        setUpViewModel()
        setupUI()
        setUpClickListener()
        setupObservers()
    }

    private fun setUpClickListener() {
        activityMainBinding.txtPopularSeeAll.setOnClickListener {
            // TODO: 02-10-2021 popular movies
            startActivity(
                Intent(context, FullListActivity::class.java).putExtra(
                    "LIST_TYPE",
                    POPULAR
                )
            )
        }

        activityMainBinding.txtTrendingSeeAll.setOnClickListener {
            // TODO: 02-10-2021 trending movies

            startActivity(
                Intent(context, FullListActivity::class.java).putExtra(
                    "LIST_TYPE",
                    TRENDING
                )
            )
        }

        activityMainBinding.txtTopRatedSeeAll.setOnClickListener {
            // TODO: 02-10-2021 top rated movies

            startActivity(
                Intent(context, FullListActivity::class.java).putExtra(
                    "LIST_TYPE",
                    TOP_RATED
                )
            )

        }

        activityMainBinding.txtUpComingSeeAll.setOnClickListener {
            // TODO: 02-10-2021 upcoming movies

            startActivity(
                Intent(context, FullListActivity::class.java).putExtra(
                    "LIST_TYPE",
                    UPCOMING
                )
            )

        }
    }

    private fun setupUI() {
//        POPULAR MOVIE ADAPTER
        popularMovieListAdapter = HorizontalMovieListAdapter(context, popularMovieList, this)
        activityMainBinding.rvPopularMovieList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        activityMainBinding.rvPopularMovieList.adapter = popularMovieListAdapter

//        TRENDING ADAPTER
        trendingMovieListAdapter = HorizontalMovieListAdapter(context, trendingMovieList, this)
        activityMainBinding.rvTrendingMovieList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        activityMainBinding.rvTrendingMovieList.adapter = trendingMovieListAdapter

//        TOP RATED ADAPTER
        topRatedMovieListAdapter = HorizontalMovieListAdapter(context, topRatedMovieList, this)
        activityMainBinding.rvTopRatedMovieList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        activityMainBinding.rvTopRatedMovieList.adapter = topRatedMovieListAdapter

//        UPCOMING MOVIE ADAPTER
        upcomingMovieListAdapter = HorizontalMovieListAdapter(context, topRatedMovieList, this)
        activityMainBinding.rvUpComingMovieList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        activityMainBinding.rvUpComingMovieList.adapter = upcomingMovieListAdapter
    }

    private fun setUpViewModel() {
        movieListViewModel = ViewModelProviders.of(
            this,
            MovieListViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MovieListViewModel::class.java)
    }

    private fun setupObservers() {
//        POPULAR MOVIES
        movieListViewModel.getPopularMovieList().observe(this, { movieResponse ->
            movieResponse?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.i("TAG", "setupObservers: ${resource.data}")

//                        hideProgressBar(activityMainBinding.progressPopular)

                        resource.data.let { movieResponse ->
                            popularMovieList.addAll(movieResponse!!.results)
                            if (popularMovieList.isNotEmpty()) {
                                popularMovieListAdapter.updateList(popularMovieList)
                            }
                        }
                    }

                    Status.ERROR -> {
                        Log.i("TAG", "setupObservers: ERROR")
//                        hideProgressBar(activityMainBinding.progressPopular)
                    }
                    Status.LOADING -> {
                        Log.i("TAG", "setupObservers: LOADING")
//                        showProgressBar(activityMainBinding.progressPopular)
                    }
                }

            }
        })

//        TRENDING MOVIES
        movieListViewModel.getTrendingMovieList().observe(this, { trendingMovieResponse ->
            trendingMovieResponse.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.i("TAG", "setupObservers: ${resource.data}")

                        resource.data.let { movieResponse ->
                            trendingMovieList.addAll(movieResponse!!.results)
                            if (trendingMovieList.isNotEmpty()) {
                                trendingMovieListAdapter.updateList(trendingMovieList)
                            }
                        }
                    }
                    Status.ERROR -> {
                        Log.i("TAG", "setupObservers: ERROR ${resource.message}")
                    }
                    Status.LOADING -> {
                        Log.i("TAG", "setupObservers: LOADING")
                    }
                }
            }

        })

//        TOP RATED
        movieListViewModel.getTopRatedMovieList().observe(this, { topRatedMovieResponse ->
            topRatedMovieResponse.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.i("TAG", "setupObservers: ${resource.data}")

                        resource.data.let { movieResponse ->
                            topRatedMovieList.addAll(movieResponse!!.results)
                            if (topRatedMovieList.isNotEmpty()) {
                                topRatedMovieListAdapter.updateList(
                                    topRatedMovieList
                                )
                            }
                        }
                    }
                    Status.ERROR -> {
                        Log.i("TAG", "setupObservers: ERROR ${resource.message}")
                    }
                    Status.LOADING -> {
                        Log.i("TAG", "setupObservers: LOADING")
                    }
                }
            }

        })

//        UPCOMING MOVIES
        movieListViewModel.getUpcomingMovieList().observe(this, { upComingMovieResponse ->
            upComingMovieResponse.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.i("TAG", "setupObservers: ${resource.data}")

                        resource.data.let { movieResponse ->
                            upcomingMovieList.addAll(movieResponse!!.results)
                            if (upcomingMovieList.isNotEmpty()) {
                                upcomingMovieListAdapter.updateList(
                                    upcomingMovieList
                                )
                            }
                        }
                    }
                    Status.ERROR -> {
                        Log.i("TAG", "setupObservers: ERROR ${resource.message}")
                    }
                    Status.LOADING -> {
                        Log.i("TAG", "setupObservers: LOADING")
                    }
                }
            }
        })
    }

    override fun movieListClick(movieDetail: MovieListResult) {
        // TODO: 04-10-2021 Movie Clicked
        Toast.makeText(context, movieDetail.originalTitle, Toast.LENGTH_SHORT).show()

        startActivity(
            Intent(context, FullPreviewActivity::class.java).putExtra(
                "movieId",
                movieDetail.id
            )
        )
    }

}