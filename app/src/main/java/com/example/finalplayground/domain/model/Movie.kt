package com.example.finalplayground.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val poster_path: String? = null,
    val adult: Boolean? = null,
    val overview: String? = null,
    val release_date: String? = null,
    val genre_ids: List<Int>? = null,
    val id: Int? = null,
    val original_title: String? = null,
    val original_language: String? = null,
    val title: String? = null,
    val backdrop_path: String? = null,
    val popularity: Double? = null,
    val vote_count: Int? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null
) : java.io.Serializable
