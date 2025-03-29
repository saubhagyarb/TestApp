package com.example.testapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil3.load

class PosterMovieAdapter(
    private val context: Context,
    private val itemList: List<Movies>
) : RecyclerView.Adapter<PosterMovieAdapter.PosterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_poster_item, parent, false)
        return PosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        val item = itemList[position]
        holder.title.text = item.movieTitle
        holder.subtitle.text = String.format("NEW • ${item.movieGenre} • ${item.movieYear} • ${item.movieType}")
        holder.image.load(item.moviePoster.toString().replace("http://","https://"))
    }

    override fun getItemCount(): Int = itemList.size

    class PosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val title: TextView = itemView.findViewById(R.id.item_title)
        val subtitle: TextView = itemView.findViewById(R.id.item_subtitle)
    }
}
