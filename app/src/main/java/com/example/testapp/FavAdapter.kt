package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.testapp.data.FavMovie

class FavAdapter(
    private val favMoviesLiveData: LiveData<List<FavMovie>>,
    lifecycleOwner: LifecycleOwner
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

        fun bind(movie: FavMovie) {
            tvTitle.text = movie.title
            time.text = movie.runtime
            imgPoster.load(movie.poster?.replace("http://", "https://"))
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

