package com.example.lifehacktest.data.remote

import com.google.gson.annotations.SerializedName


//@kotlinx.serialization.Serializable
data class Company(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("lat")
    val lat: Float? = null,
    @SerializedName("lon")
    val lon: Float? = null,
    @SerializedName("www")
    val vebSite: String? = null,
    @SerializedName("phone")
    val phoneNumber: String? = null,
)
