package com.example.utspam

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("users")
    fun getUser(@Query("page") page:Int): Call<ApiResponse>

}