package com.e.retroftwithcoroutines.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Munish Chandel
 */
//Retrofit service class
interface PersonApi {
//	suspend function allows us to run this method inside a coroutine.
    @GET("/api/users/2")
    suspend fun getPerson(): Response<ServiceResponse<Person>>
}

//Service response
data class ServiceResponse<T>(@SerializedName("data") val data: T)

//service response this class have person details
data class Person(
    @SerializedName("id") val id: Long,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("avatar") val avatar: String
)