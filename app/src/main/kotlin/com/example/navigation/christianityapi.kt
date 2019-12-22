package com.example.navigation

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL2="http://192.168.43.206:8080/Myapi/"

interface christianityapi {
    @GET("christianity.php")
    fun getplaces(): Call<List<place>>
    companion object{
        operator fun invoke():christianityapi{
            return Retrofit.Builder().baseUrl(BASE_URL2).addConverterFactory(GsonConverterFactory.create()).build().create(christianityapi::class.java)
        }
    }
}