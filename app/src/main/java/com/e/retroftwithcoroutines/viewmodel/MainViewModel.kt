package com.e.retroftwithcoroutines.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.e.retroftwithcoroutines.api.ApiClient
import com.e.retroftwithcoroutines.api.PersonApi
import com.e.retroftwithcoroutines.api.Resource
import kotlinx.coroutines.Dispatchers

//viewmodel class interacting  with the service and fetches the data from network to fragment using Kotlin coroutine.
// So the request will be automatically cancelled if fragment is destroyed.
class MainViewModel : ViewModel() {

    //	liveData is kotlin extension function that enables us to create and run a Kotlin coroutine with viewModelScope.
    fun loadData() = liveData(Dispatchers.IO) {
       //emit accepts the data class for loading and success and
        // emits the changes in data to the observer variables
        emit(Resource.loading())
        //creating object to the interface
        val api = ApiClient.createService(PersonApi::class.java)
        val response = api.getPerson()
        if(response.isSuccessful) {
            emit(Resource.success(response.body()?.data))
        }
    }
}
