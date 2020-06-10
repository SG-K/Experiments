package com.e.retroftwithcoroutines.api

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Munish Chandel
 */
//In this class creating an instance of OkHttpClient and Retrofit client .
object ApiClient {

    private val okHttpClient = OkHttpClient()

    private val retrofit: Retrofit by lazy {

           Log.e("AppClient", "Creating Retrofit Client")
        val builder = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient = okHttpClient.newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
        builder.client(client).build()
    }

    //creating instance of service by passing the service class
    fun <T> createService(tClass: Class<T>?): T {
        return retrofit!!.create(tClass)
    }
}