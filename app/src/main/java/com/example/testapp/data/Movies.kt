package com.example.testapp.data

import com.google.gson.annotations.SerializedName

data class Movies (

    @SerializedName("Title"      ) var movieTitle      : String?           = null,
    @SerializedName("Year"       ) var movieYear       : String?           = null,
    @SerializedName("Rated"      ) var movieRated      : String?           = null,
    @SerializedName("Released"   ) var movieReleased   : String?           = null,
    @SerializedName("Runtime"    ) var movieRuntime    : String?           = null,
    @SerializedName("Genre"      ) var movieGenre      : String?           = null,
    @SerializedName("Director"   ) var movieDirector   : String?           = null,
    @SerializedName("Writer"     ) var movieWriter     : String?           = null,
    @SerializedName("Actors"     ) var movieActors     : String?           = null,
    @SerializedName("Plot"       ) var moviePlot       : String?           = null,
    @SerializedName("Language"   ) var movieLanguage   : String?           = null,
    @SerializedName("Country"    ) var movieCountry    : String?           = null,
    @SerializedName("Awards"     ) var movieAwards     : String?           = null,
    @SerializedName("Poster"     ) var moviePoster     : String?           = null,
    @SerializedName("Metascore"  ) var movieMetascore  : String?           = null,
    @SerializedName("imdbRating" ) var movieimdbRating : String?           = null,
    @SerializedName("imdbVotes"  ) var movieimdbVotes  : String?           = null,
    @SerializedName("imdbID"     ) var movieimdbID     : String?           = null,
    @SerializedName("Type"       ) var movieType       : String?           = null,
    @SerializedName("Response"   ) var movieResponse   : String?           = null,
    @SerializedName("Images"     ) var movieImages     : ArrayList<String> = arrayListOf()

)