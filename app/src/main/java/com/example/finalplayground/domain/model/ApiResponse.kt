package com.example.finalplayground.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val page: Int,
    val results: List<Movie>? = null,
    val dates: Dates? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)
