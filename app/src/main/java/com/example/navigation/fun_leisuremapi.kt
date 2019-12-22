package com.example.navigation

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL9="http://192.168.43.206:8080/Myapi/"

interface fun_leisureapi {
    @GET("fun.php")
    fun getplaces(): Call<List<place>>
    companion object{
        operator fun invoke():fun_leisureapi{
            return Retrofit.Builder().baseUrl(BASE_URL9).addConverterFactory(GsonConverterFactory.create()).build().create(fun_leisureapi::class.java)
        }
    }
}