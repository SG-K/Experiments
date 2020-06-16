package com.e.retroftwithcoroutines.model

import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class tittleModel(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String?

)
