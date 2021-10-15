package com.pratik.moviemania.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pratik.moviemania.data.models.MovieListResult
import com.pratik.moviemania.databinding.LayoutMovieListItemGridBinding

class GridMovieListAdapter(
    private val context: Context,
    var movieList: ArrayList<MovieListResult>
) : RecyclerView.Adapter<GridMovieListAdapter.ViewHolder>() {

    class ViewHolder(val layoutMovieListItemGridBinding: LayoutMovieListItemGridBinding) :
        RecyclerView.ViewHolder(layoutMovieListItemGridBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutMovieListItemGridBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.layoutMovieListItemGridBinding.movieListResult = movieList[position]
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(movieList: ArrayList<MovieListResult>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }
}