package com.example.lifehacktest.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("test.php")
    suspend fun getCompanyList(): Response<List<CompanyShort>>

    @GET("test.php")
    suspend fun getCompanyInfo(@Query("id") companyId: Int): Response<List<Company>>

    companion object {
        const val BASE_URL = "https://lifehack.studio/test_task/"
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}