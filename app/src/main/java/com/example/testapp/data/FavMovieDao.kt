package com.example.testapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavMovieDao {
    @Insert
    fun insertFavMovie(favMovie: FavMovie)

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavMovies(): LiveData<List<FavMovie>>

    @Query("DELETE FROM favorite_movies WHERE title = :title")
    fun deleteFavMovie(title: String)

    @Query("SELECT * FROM favorite_movies WHERE title = :title")
    fun getFavMovieByTitle(title: String): LiveData<FavMovie?>
}