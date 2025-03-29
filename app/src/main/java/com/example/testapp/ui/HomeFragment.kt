package com.example.testapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.DataPass
import com.example.testapp.MovieAdapter
import com.example.testapp.Movies
import com.example.testapp.PosterAdapter
import com.example.testapp.PosterMovieAdapter
import com.example.testapp.R
import com.example.testapp.data.FavMovieViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class HomeFragment : Fragment(){
    private lateinit var posterRecyclerView: RecyclerView
    private lateinit var trendingRecyclerView: RecyclerView
    private lateinit var continueRecyclerView: RecyclerView
    private lateinit var latestRecyclerView: RecyclerView
    private lateinit var trendingArrowBtn: ImageButton
    private lateinit var latestArrowBtn: ImageButton

    val dataPass = object : DataPass {
        override fun passData(favoritesCount: Int) {
            Toast.makeText(context, "Favorites Count: $favoritesCount", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val context = requireContext()

        posterRecyclerView = view.findViewById(R.id.posterRecyclerView)
        trendingRecyclerView = view.findViewById(R.id.movieRecyclerView)
        continueRecyclerView = view.findViewById(R.id.continueRecyclerView)
        latestRecyclerView = view.findViewById(R.id.latestRecyclerView)
        trendingArrowBtn = view.findViewById(R.id.trendingArrowBtn)
        latestArrowBtn = view.findViewById(R.id.latestArrowBtn)

        posterRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        trendingRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        continueRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        latestRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        fetchMovies()

        return view
    }

    private fun fetchMovies() {
        lifecycleScope.launch(Dispatchers.IO) {
            runCatching {
                val jsonResponse =
                    URL("https://gist.githubusercontent.com/saniyusuf/406b843afdfb9c6a86e25753fe2761f4/raw/523c324c7fcc36efab8224f9ebb7556c09b69a14/Film.JSON").readText()
                val moviesList = Gson().fromJson(jsonResponse, Array<Movies>::class.java).toList()
                val fabMoviesViewModel = FavMovieViewModel(requireActivity().application)
                 withContext(Dispatchers.Main) {
                    posterRecyclerView.adapter = PosterMovieAdapter(requireContext(), moviesList)
                    trendingRecyclerView.adapter = MovieAdapter(moviesList, fabMoviesViewModel, viewLifecycleOwner, dataPass)
                    continueRecyclerView.adapter = MovieAdapter(moviesList, fabMoviesViewModel, viewLifecycleOwner, dataPass)
                    latestRecyclerView.adapter = PosterAdapter(movies = moviesList)
                }
            }.getOrElse {
                Log.e("Error", "Failed to fetch movies")
            }
        }
    }
}
