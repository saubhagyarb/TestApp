package com.example.testapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class HomeScreen : AppCompatActivity() {
    private lateinit var posterRecyclerView: RecyclerView
    private lateinit var trendingRecyclerView: RecyclerView
    private lateinit var continueRecyclerView: RecyclerView
    private lateinit var latestRecyclerView: RecyclerView
    private lateinit var trendingArrowBtn : ImageButton
    private lateinit var latestArrowBtn : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        posterRecyclerView = findViewById(R.id.posterRecyclerView)
        trendingRecyclerView = findViewById(R.id.movieRecyclerView)
        continueRecyclerView = findViewById(R.id.continueRecyclerView)
        latestRecyclerView = findViewById(R.id.latestRecyclerView)
        trendingArrowBtn = findViewById(R.id.trendingArrowBtn)
        latestArrowBtn = findViewById(R.id.latestArrowBtn)

        posterRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        trendingRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        continueRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        latestRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        fetchMovies()
//        trendingArrowBtn.setOnClickListener {arrowOnclick(trendingArrowBtn)}
//        latestArrowBtn.setOnClickListener {arrowOnclick(latestArrowBtn)}
    }

    private fun fetchMovies() {
        lifecycleScope.launch(Dispatchers.IO) {
            runCatching {
                val jsonResponse =
                    URL("https://gist.githubusercontent.com/saniyusuf/406b843afdfb9c6a86e25753fe2761f4/raw/523c324c7fcc36efab8224f9ebb7556c09b69a14/Film.JSON").readText()
                val moviesList = Gson().fromJson(jsonResponse, Array<Movies>::class.java).toList()
                Log.d("Load", "fetchMovies: $moviesList")
                withContext(Dispatchers.Main) {
                    posterRecyclerView.adapter = PosterMovieAdapter(this@HomeScreen, moviesList)
                    trendingRecyclerView.adapter = MovieAdapter(moviesList)
                    continueRecyclerView.adapter = MovieAdapter(moviesList)
                    latestRecyclerView.adapter = PosterAdapter(moviesList)
                }
            }.getOrElse {
                Log.e("Error", "Failed to fetch movies")
            }
        }
    }

//    private fun arrowOnclick(btn : ImageButton){
//        val visibility : Boolean = true
//    }
}