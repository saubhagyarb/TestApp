package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.FavMovie
import com.example.testapp.data.FavMovieViewModel

class MovieAdapter(
    private val movies: List<Movies>,
    private val favMovieViewModel: FavMovieViewModel,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movieTitle)
        val ratingText: TextView = view.findViewById(R.id.ratingText)
        val category: TextView = view.findViewById(R.id.categoryText)
        val imagesRecyclerView: RecyclerView = view.findViewById(R.id.imagesRecyclerView)
        val favoriteButton: ImageButton = view.findViewById(R.id.favoriteButton)

        val lm = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        var ga = GalleryAdapter()


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        //todo
        //don't create unwanted instances

        holder.title.text = movie.Title
        holder.ratingText.text = movie.imdbRating
        holder.category.text = movie.Genre
        holder.imagesRecyclerView.layoutManager = holder.lm

        holder.ga.imagesInit(movie.Images)
        holder.imagesRecyclerView.adapter = holder.ga


        //todo
        // observer use only activity and fragments

        //1 -
        //2
        //3
        //4
        favMovieViewModel.getFavMovieByTitle(movie.Title.toString()).observe(lifecycleOwner, Observer { favMovie ->

            val isFavorite = favMovie != null

            updateFavoriteButton(holder.favoriteButton, isFavorite)

            holder.favoriteButton.setOnClickListener {
                if (isFavorite) {
                    favMovieViewModel.deleteFavMovie(movie.Title.toString())
                    Toast.makeText(holder.itemView.context, "${movie.Title} removed from favorites", Toast.LENGTH_SHORT).show()
                } else {
                    val newFavMovie = FavMovie(title = movie.Title, poster = movie.Poster, runtime = movie.Runtime)
                    favMovieViewModel.insertFavMovie(newFavMovie)
                    Toast.makeText(holder.itemView.context, "${movie.Title} added to favorites", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun updateFavoriteButton(favoriteButton: ImageButton, isFavorite: Boolean) {
        val context = favoriteButton.context
        favoriteButton.setImageDrawable(
            ContextCompat.getDrawable(
                context, if (isFavorite) R.drawable.favorite_filled else R.drawable.favorite
            )
        )
    }

    override fun getItemCount(): Int = movies.size
}
