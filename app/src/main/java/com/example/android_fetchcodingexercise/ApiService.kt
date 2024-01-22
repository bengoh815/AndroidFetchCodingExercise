package com.example.android_fetchcodingexercise

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    fun getData(): Call<List<DataItem>>
}