package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MovieAdapter(private val movies: List<Movies>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movieTitle)
        val ratingText: TextView = view.findViewById(R.id.ratingText)
        val category: TextView = view.findViewById(R.id.categoryText)
        val imagesRecyclerView: RecyclerView = view.findViewById(R.id.imagesRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.Title
        holder.ratingText.text = movie.imdbRating
        holder.category.text = movie.Genre
        holder.imagesRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.VERTICAL, false)
        val galleryAdapter = GalleryAdapter(movie.Images)
        holder.imagesRecyclerView.adapter = galleryAdapter
    }

    override fun getItemCount(): Int = movies.size
}
