package com.example.finalplayground.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerialName("base_url")
    val baseURL: String? = null,
    @SerialName("secure_base_url")
    val secureBaseURL: String? = null,
    @SerialName("backdrop_sizes")
    val backdropSizes: List<String>? = null,
    @SerialName("logo_sizes")
    val logoSizes: List<String>? = null,
    @SerialName("poster_sizes")
    val posterSizes: List<String>? = null,
    @SerialName("profile_sizes")
    val profileSizes: List<String>? = null,
    @SerialName("still_sizes")
    val stillSizes: List<String>? = null
) : java.io.Serializable
