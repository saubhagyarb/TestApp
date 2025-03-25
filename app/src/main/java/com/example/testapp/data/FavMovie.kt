package com.example.testapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavMovie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String? = null,
    val poster: String? = null,
    val runtime: String? = null
)