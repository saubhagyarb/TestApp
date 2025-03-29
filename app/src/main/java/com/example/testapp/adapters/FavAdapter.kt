package com.example.testapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.testapp.R
import com.example.testapp.data.FavMovie
import com.example.testapp.data.FavMovieViewModel

class FavAdapter(
    favMoviesLiveData: LiveData<List<FavMovie>>,
    lifecycleOwner: LifecycleOwner,
    private val favMovieViewModel: FavMovieViewModel
) : RecyclerView.Adapter<FavAdapter.FavViewHolder>() {

    private var favMovies: List<FavMovie> = emptyList()

    init {
        favMoviesLiveData.observe(lifecycleOwner) { updatedMovies ->
            favMovies = updatedMovies
        }

    }

    inner class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPoster: ImageView = itemView.findViewById(R.id.imgPoster)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val time: TextView = itemView.findViewById(R.id.time)
        private val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)

        fun bind(movie: FavMovie) {
            tvTitle.text = movie.title
            time.text = movie.runtime
            imgPoster.load(movie.poster?.replace("http://", "https://"))

            btnDelete.setOnClickListener {
                favMovieViewModel.deleteFavMovie(movie.title.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fev_movie_item, parent, false)
        return FavViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(favMovies[position])
    }

    override fun getItemCount(): Int = favMovies.size
}