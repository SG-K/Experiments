package com.e.retroftwithcoroutines.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.e.retroftwithcoroutines.api.ApiClient
import com.e.retroftwithcoroutines.api.PersonApi
import com.e.retroftwithcoroutines.api.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {

    fun loadData() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val api = ApiClient.createService(PersonApi::class.java)
        val response = api.getPerson()
        if(response.isSuccessful) {
            emit(Resource.success(response.body()?.data))
        }
    }
}
