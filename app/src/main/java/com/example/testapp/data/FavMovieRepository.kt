package com.example.testapp.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavMovieRepository(private val favMovieDao: FavMovieDao) {

    val allFavMovies: LiveData<List<FavMovie>> = favMovieDao.getAllFavMovies()

    suspend fun insertFavMovie(favMovie: FavMovie) {
        withContext(Dispatchers.IO) {
            favMovieDao.insertFavMovie(favMovie)
        }
    }

    suspend fun deleteFavMovie(title: String) {
        withContext(Dispatchers.IO) {
            favMovieDao.deleteFavMovie(title)
        }
    }

    suspend fun getFavMovieByTitle(title: String): FavMovie? {
        return withContext(Dispatchers.IO) {
            favMovieDao.getFavMovieByTitle(title)
        }
    }
}
