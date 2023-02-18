package com.example.lifehacktest.data.remote


@kotlinx.serialization.Serializable
data class Company(
    val id: Int,
    val name: String,
    val img: String,
    val description: String? = null,
    val lat: Float? = null,
    val lon: Float? = null,
    val www: String? = null,
    val phone: String? = null,
)
