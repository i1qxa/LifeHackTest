package com.example.lifehacktest.data.remote

import com.google.gson.annotations.SerializedName

data class Company(
    val id:Int,
    val name:String,
    val img:String,
    val description:String,
    val lat:Float,
    val lon:Float,
    @SerializedName("www")
    val vebSite:String?,
    @SerializedName("phone")
    val phoneNumber:String?,
)
