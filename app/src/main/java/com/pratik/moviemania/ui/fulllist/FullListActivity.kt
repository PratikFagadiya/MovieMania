package com.pratik.moviemania.ui.fulllist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.pratik.moviemania.R
import com.pratik.moviemania.api.RetrofitBuilder
import com.pratik.moviemania.data.models.MovieListResult
import com.pratik.moviemania.data.remote.ApiHelper
import com.pratik.moviemania.databinding.ActivityFullListBinding
import com.pratik.moviemania.ui.adapters.GridMovieListAdapter
import com.pratik.moviemania.ui.base.BaseActivity
import com.pratik.moviemania.ui.main.MovieListViewModel
import com.pratik.moviemania.ui.main.MovieListViewModelFactory
import com.pratik.moviemania.utils.Constants.POPULAR
import com.pratik.moviemania.utils.Constants.TOP_RATED
import com.pratik.moviemania.utils.Constants.TRENDING
import com.pratik.moviemania.utils.Constants.UPCOMING
import com.pratik.moviemania.utils.Status

class FullListActivity : BaseActivity() {

    lateinit var activityFullListBinding: ActivityFullListBinding
    lateinit var movieListViewModel: MovieListViewModel
    var listType: String = POPULAR
    private var movieList = ArrayList<MovieListResult>()
    lateinit var gridMovieListAdapter: GridMovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFullListBinding = setView(this, R.layout.activity_full_list)

        listType = intent.getStringExtra("LIST_TYPE").toString()

        setUpViewModel()
        setUpUI()
        setupObservers()
    }

    private fun setUpViewModel() {
        movieListViewModel = ViewModelProviders.of(
            this, MovieListViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MovieListViewModel::class.java)

        activityFullListBinding.movieListViewModel = movieListViewModel
        activityFullListBinding.lifecycleOwner = this
    }

    private fun setUpUI() {
        gridMovieListAdapter = GridMovieListAdapter(context, movieList)
        activityFullListBinding.rvFullList.layoutManager =
            GridLayoutManager(context, 2)
        activityFullListBinding.rvFullList.adapter = gridMovieListAdapter
    }

    private fun setupObservers() {
        movieListViewModel.listType.postValue(listType)
        movieListViewModel.listType.observeForever { value ->
            Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
            when (value) {

                POPULAR -> {
//              POPULAR MOVIES
                    movieListViewModel.getPopularMovieList().observe(this, { movieResponse ->
                        movieResponse?.let { resource ->
                            when (resource.status) {
                                Status.SUCCESS -> {
                                    Log.i("TAG", "setupObservers: ${resource.data}")

                                    resource.data.let { movieResponse ->
                                        movieList.addAll(movieResponse!!.results)
                                        if (movieList.isNotEmpty()) {
                                            gridMovieListAdapter.updateList(movieList)
                                        }
                                    }
                                }

                                Status.ERROR -> {
                                    Log.i("TAG", "setupObservers: ERROR")
                                }
                                Status.LOADING -> {
                                    Log.i("TAG", "setupObservers: LOADING")
                                }
                            }

                        }
                    })
                }

                TRENDING -> {
//              TRENDING MOVIES
                    movieListViewModel.getTrendingMovieList()
                        .observe(this, { trendingMovieResponse ->
                            trendingMovieResponse.let { resource ->
                                when (resource.status) {
                                    Status.SUCCESS -> {
                                        Log.i("TAG", "setupObservers: ${resource.data}")

                                        resource.data.let { movieResponse ->
                                            movieList.addAll(movieResponse!!.results)
                                            if (movieList.isNotEmpty()) {
                                                gridMovieListAdapter.updateList(movieList)
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

                TOP_RATED -> {
//              TOP RATED
                    movieListViewModel.getTopRatedMovieList()
                        .observe(this, { topRatedMovieResponse ->
                            topRatedMovieResponse.let { resource ->
                                when (resource.status) {
                                    Status.SUCCESS -> {
                                        Log.i("TAG", "setupObservers: ${resource.data}")

                                        resource.data.let { movieResponse ->
                                            movieList.addAll(movieResponse!!.results)
                                            if (movieList.isNotEmpty()) {
                                                gridMovieListAdapter.updateList(
                                                    movieList
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

                UPCOMING -> {
//              UPCOMING MOVIES
                    movieListViewModel.getUpcomingMovieList()
                        .observe(this, { upComingMovieResponse ->
                            upComingMovieResponse.let { resource ->
                                when (resource.status) {
                                    Status.SUCCESS -> {
                                        Log.i("TAG", "setupObservers: ${resource.data}")

                                        resource.data.let { movieResponse ->
                                            movieList.addAll(movieResponse!!.results)
                                            if (movieList.isNotEmpty()) {
                                                gridMovieListAdapter.updateList(
                                                    movieList
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

            }
        }
    }

}