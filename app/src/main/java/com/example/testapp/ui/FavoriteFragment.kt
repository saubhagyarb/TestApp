package com.example.testapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.adapters.FavAdapter
import com.example.testapp.R
import com.example.testapp.data.FavMovieViewModel

class FavoriteFragment : Fragment() {
    private lateinit var favRecyclerView: RecyclerView
    private lateinit var emptyStateLayout: TextView
    lateinit var fabMovieViewModel: FavMovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        val context = requireContext()

        favRecyclerView = view.findViewById(R.id.favFragmentRecyclerView)
        emptyStateLayout = view.findViewById(R.id.emptyStateLayout)

        favRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        fabMovieViewModel = FavMovieViewModel(requireActivity().application)

        fetchFavoriteMovies()

        return view
    }

    private fun fetchFavoriteMovies() {
        val fabMovies = fabMovieViewModel.allFavMovies

        fabMovies.observe(viewLifecycleOwner) { favMoviesList ->
            Log.d("FavoriteFragment", "Favorite movies: $favMoviesList")

            if (favMoviesList.isNullOrEmpty()) {
                emptyStateLayout.visibility = View.VISIBLE
                favRecyclerView.visibility = View.GONE
            } else {
                emptyStateLayout.visibility = View.GONE
                favRecyclerView.visibility = View.VISIBLE
            }

            favRecyclerView.adapter = FavAdapter(fabMovies, viewLifecycleOwner, fabMovieViewModel)
        }
    }

}