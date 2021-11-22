package com.example.finalplayground.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguage(
    @SerialName("iso_639_1")
    val iso639_1: String,
    val name: String
)
