package com.example.testapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavMovieViewModel(application: Application) : AndroidViewModel(application)  {
    val repository = FavMovieRepository(FavMovieDatabase.getInstance(application).favMovieDao())
    val allFavMovies = repository.allFavMovies

    fun insertFavMovie(favMovie: FavMovie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertFavMovie(favMovie)
    }

    fun deleteFavMovie(title: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteFavMovie(title)
    }

    fun getFavMovieByTitle(title: String) : LiveData<FavMovie?> {
        return repository.getFavMovieByTitle(title)
    }
}