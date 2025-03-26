package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil3.load

class PosterAdapter(private val movies: List<Movies>) :
    RecyclerView.Adapter<PosterAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moviePoster: ImageView = itemView.findViewById(R.id.moviePoster)
        private val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
        private val movieDuration: TextView = itemView.findViewById(R.id.movieDuration)

        fun bind(movie: Movies) {
            movieTitle.text = movie.Title
            movieDuration.text = movie.Runtime
            moviePoster.load(movie.Poster.toString().replace("http://","https://"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poster_with_duration, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}