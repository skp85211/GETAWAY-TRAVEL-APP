package com.example.navigation

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL8="http://192.168.43.206:8080:8080/Myapi/"

interface nightlifeapi {
    @GET("nightlife.php")
    fun getplaces(): Call<List<place>>
    companion object{
        operator fun invoke():nightlifeapi{
            return Retrofit.Builder().baseUrl(BASE_URL8).addConverterFactory(GsonConverterFactory.create()).build().create(nightlifeapi::class.java)
        }
    }
}