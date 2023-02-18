package com.example.lifehacktest.data.remote

@kotlinx.serialization.Serializable
data class CompanyShort(
    val id: Int,
    val name: String,
    val img: String,
)
