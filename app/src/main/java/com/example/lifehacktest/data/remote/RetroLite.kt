package com.example.lifehacktest.data.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroLite {

    @GET("test.php")
    suspend fun getCompanyInfoLite(@Query("id") companyId: Int): Response<String>

    companion object {
        private val contentType = MediaType.get("application/json")
        const val BASE_URL = "https://lifehack.studio/test_task/"
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            val json = Json{
                ignoreUnknownKeys = true

            }
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //.addConverterFactory(json.asConverterFactory(contentType))
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}

//interface RetrofitService {
//
//    @GET("test.php")
//    suspend fun getCompanyList(): Response<List<CompanyShort>>
//
//    @GET("test.php")
//    suspend fun getCompanyInfo(@Query("id") companyId: Int): Response<List<Company>>
//
//    companion object {
//        const val BASE_URL = "https://lifehack.studio/test_task/"
//        var retrofitService: RetrofitService? = null
//        fun getInstance(): RetrofitService {
//            if (retrofitService == null) {
//                val retrofit = Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                retrofitService = retrofit.create(RetrofitService::class.java)
//            }
//            return retrofitService!!
//        }
//    }
//}