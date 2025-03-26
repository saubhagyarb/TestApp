package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil3.load

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private var images: ArrayList<String>? = null


    fun imagesInit(data :  ArrayList<String>) {
        images = data
    }

    class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.galleryImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.imageView.load(images?.get(position))
    }

    override fun getItemCount(): Int = images?.size ?: 0
}
