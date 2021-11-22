package com.example.finalplayground.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
    val images: Images? = null,
    val changeKeys: List<String>? = null
) : java.io.Serializable
