package com.example.testapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.FavMovie
import com.example.testapp.data.FavMovieViewModel

@SuppressLint("NotifyDataSetChanged")
class MovieAdapter(
    private val movies: List<Movies>,
    private val favMovieViewModel: FavMovieViewModel,
    lifecycleOwner: LifecycleOwner,
    val dataPass: DataPass,

) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val favoriteMovies = mutableSetOf<String>()


    init {
        favMovieViewModel.allFavMovies.observe(lifecycleOwner) { favMovies ->
            favoriteMovies.clear()
            favoriteMovies.addAll(favMovies.mapNotNull { it.title }.toSet())
            notifyDataSetChanged()
        }
    }



    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movieTitle)
        val ratingText: TextView = view.findViewById(R.id.ratingText)
        val category: TextView = view.findViewById(R.id.categoryText)
        val imagesRecyclerView: RecyclerView = view.findViewById(R.id.imagesRecyclerView)
        val favoriteButton: ImageButton = view.findViewById(R.id.favoriteButton)
        val layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        var galleryAdapter = GalleryAdapter()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.title.text = movie.movieTitle
        holder.ratingText.text = movie.movieimdbRating
        holder.category.text = movie.movieGenre
        holder.imagesRecyclerView.layoutManager = holder.layoutManager
        holder.galleryAdapter.imagesInit(movie.movieImages)
        holder.imagesRecyclerView.adapter = holder.galleryAdapter

        val isFavorite = favoriteMovies.contains(movie.movieTitle)
        updateFavoriteButton(holder.favoriteButton, isFavorite)

        holder.favoriteButton.setOnClickListener {
            if (isFavorite) {
                favMovieViewModel.deleteFavMovie(movie.movieTitle.toString())
                favoriteMovies.remove(movie.movieTitle)
                dataPass.passData(favoriteMovies.size)
//                Toast.makeText(holder.itemView.context, "${movie.Title} removed from favorites", Toast.LENGTH_SHORT).show()

//                ref.task1OnFRg(favoriteMovies.size)

            } else {
                favMovieViewModel.insertFavMovie(FavMovie(title = movie.movieTitle, poster = movie.moviePoster, runtime = movie.movieRuntime))
                favoriteMovies.add(movie.movieTitle.toString())
                dataPass.passData(favoriteMovies.size)
//                Toast.makeText(holder.itemView.context, "${movie.Title} added to favorites", Toast.LENGTH_SHORT).show()


//                ref.task1OnFRg(favoriteMovies.size)
            }
            notifyItemChanged(position)
        }
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
