package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.FavMovie
import com.example.testapp.data.FavMovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieAdapter(
    private val movies: List<Movies>,
    private val favMovieRepository: FavMovieRepository
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movieTitle)
        val ratingText: TextView = view.findViewById(R.id.ratingText)
        val category: TextView = view.findViewById(R.id.categoryText)
        val imagesRecyclerView: RecyclerView = view.findViewById(R.id.imagesRecyclerView)
        val favoriteButton: ImageButton = view.findViewById(R.id.favoriteButton)
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
        holder.imagesRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        val galleryAdapter = GalleryAdapter(movie.Images)
        holder.imagesRecyclerView.adapter = galleryAdapter

        var isFavorite = false
        CoroutineScope(Dispatchers.IO).launch {
            val existingFavorite = favMovieRepository.getFavMovieByTitle(movie.Title.toString())
            withContext(Dispatchers.Main) {
                isFavorite = existingFavorite != null
                updateFavoriteButton(holder.favoriteButton, isFavorite)
            }
        }

        holder.favoriteButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                if (isFavorite) {
                    favMovieRepository.deleteFavMovie(movie.Title.toString())
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            holder.itemView.context,
                            "${movie.Title} removed from favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                        isFavorite = false
                        updateFavoriteButton(holder.favoriteButton, false)
                    }
                } else {
                    val favMovie = FavMovie(
                        title = movie.Title,
                        poster = movie.Poster,
                        runtime = movie.Runtime
                    )
                    favMovieRepository.insertFavMovie(favMovie)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            holder.itemView.context,
                            "${movie.Title} added to favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                        isFavorite = true
                        updateFavoriteButton(holder.favoriteButton, true)
                    }
                }
            }
        }
    }

    private fun updateFavoriteButton(favoriteButton: ImageButton, isFavorite: Boolean) {
        val context = favoriteButton.context
        if (isFavorite) {
            favoriteButton.setImageDrawable(
                ContextCompat.getDrawable(context, R.drawable.favorite_filled)
            )
        } else {
            favoriteButton.setImageDrawable(
                ContextCompat.getDrawable(context, R.drawable.favorite)
            )
        }
    }

    override fun getItemCount(): Int = movies.size
}