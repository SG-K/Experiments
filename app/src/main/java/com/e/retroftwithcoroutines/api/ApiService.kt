package com.e.retroftwithcoroutines.api


import com.e.retroftwithcoroutines.model.tittleModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/posts/{id}")
     fun getTodo(@Path("id") id: Int): Deferred<Response<tittleModel>>
}