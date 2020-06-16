package com.e.retroftwithcoroutines.api


import com.e.retroftwithcoroutines.model.tittleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/posts/{id}")
    suspend fun getTodo(@Path("id") id: Int): Response<tittleModel>
}