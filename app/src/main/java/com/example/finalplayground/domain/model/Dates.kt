package com.example.finalplayground.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String? = null,
    val minimum: String? = null
)
