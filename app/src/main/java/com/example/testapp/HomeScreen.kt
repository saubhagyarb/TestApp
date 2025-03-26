package com.example.testapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.testapp.data.FavMovieViewModel
import com.example.testapp.ui.AboutFragment
import com.example.testapp.ui.FavoriteFragment
import com.example.testapp.ui.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeScreen : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.homeScreen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val homeFragment = HomeFragment()
        val favoriteFragment = FavoriteFragment()
        val aboutFragment = AboutFragment()
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        setFragment(homeFragment)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mHome -> setFragment(homeFragment)
                R.id.mFavorite -> setFragment(favoriteFragment)
                R.id.mAbout -> setFragment(aboutFragment)
            }
            true
        }

        val favMovieViewModel = FavMovieViewModel(application)
        favMovieViewModel.allFavMovies.observe(this@HomeScreen) { favMovies ->
            bottomNavigationView.getOrCreateBadge(R.id.mFavorite).apply {
                number = favMovies.size
                isVisible = number > 0
            }
        }

    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.homeFragmentContainer, fragment)
            commit()
        }
    }
}