package com.pratik.moviemania.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pratik.moviemania.data.models.MovieListResult
import com.pratik.moviemania.databinding.LayoutMovieListItemBinding
import com.pratik.moviemania.ui.callbacks.MovieListClickListener

class HorizontalMovieListAdapter(
    private val context: Context,
    private var popularMovieList: List<MovieListResult>,
    private val movieListClick: MovieListClickListener
) : RecyclerView.Adapter<HorizontalMovieListAdapter.ViewHolder>() {

    class ViewHolder(val layoutMovieListItem: LayoutMovieListItemBinding) :
        RecyclerView.ViewHolder(layoutMovieListItem.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutMovieListItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.layoutMovieListItem.movieListResult = popularMovieList[position]
        holder.layoutMovieListItem.movieListClick = movieListClick
    }

    override fun getItemCount(): Int {
        return 10.coerceAtMost(popularMovieList.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(popularMovieList: ArrayList<MovieListResult>) {
        this.popularMovieList = popularMovieList
        notifyDataSetChanged()
    }
}