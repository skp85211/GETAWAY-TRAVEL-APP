package com.example.navigation

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL10="http://192.168.43.206:8080/Myapi/"

interface listapi {
    @GET("list.php")
    fun getplaces(): Call<List<place>>
    companion object{
        operator fun invoke():listapi{
            return Retrofit.Builder().baseUrl(BASE_URL10).addConverterFactory(GsonConverterFactory.create()).build().create(listapi::class.java)
        }
    }
}