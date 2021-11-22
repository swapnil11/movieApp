package com.example.finalplayground.ui.model

import com.example.finalplayground.domain.model.ApiResponse
import com.example.finalplayground.domain.model.Configuration
import com.example.finalplayground.domain.model.Movie

sealed class MovieContentHolder {
    abstract val configuration: Configuration
    abstract val movieType: MovieType

    abstract fun isContentAvailable(): Boolean
}

enum class MovieType(val value: Int) {
    LATEST_MOVIE_TYPE(0),
    POPULAR_MOVIE_TYPE(1),
    NOW_PLAYING_MOVIE_TYPE(2),
    TOP_RATED_MOVIE_TYPE(3),
    UPCOMING_MOVIE_TYPE(4);
}

data class LatestMoviesContentHolder(
    val movie: Movie?,
    override val configuration: Configuration,
    override val movieType: MovieType = MovieType.LATEST_MOVIE_TYPE
) : MovieContentHolder() {
    override fun isContentAvailable(): Boolean = movie != null
}

data class ListOfMoviesContentHolder(
    val response: ApiResponse?,
    override val configuration: Configuration,
    val viewTypeOfMovie: MovieType
) : MovieContentHolder() {
    override val movieType = viewTypeOfMovie

    override fun isContentAvailable(): Boolean = response != null
}
