package com.example.finalplayground.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetail(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,

    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,

    @SerialName("imdb_id")
    val imdbID: String,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>,

    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>,

    @SerialName("release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long
)
