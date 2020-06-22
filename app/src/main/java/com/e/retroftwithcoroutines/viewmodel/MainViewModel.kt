package com.e.retroftwithcoroutines.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.retroftwithcoroutines.api.ApiHelper
import com.e.retroftwithcoroutines.model.tittleModel
import com.e.retroftwithcoroutines.api.Result
import kotlinx.coroutines.launch

//viewmodel class interacting  with the service and fetches the data from network to activity using Kotlin coroutine.
 class MainViewModel : ViewModel() {

        val successPost: MutableLiveData<tittleModel> by lazy {
            MutableLiveData<tittleModel>()
        }

        val errorMessage: MutableLiveData<String> by lazy {
            MutableLiveData<String>()
        }

        val loadingMessage: MutableLiveData<Boolean> by lazy {
            MutableLiveData<Boolean>()
        }

        //Service calling by launching the coroutine
        fun getTodoFromServer() {

            viewModelScope.launch {
                loadingMessage.postValue(true)

                try {
                val retrofitPost = ApiHelper.getTodoRequest(1)
                when (retrofitPost) {
                        is Result.Success -> {
                            loadingMessage.postValue(false)
                            successPost.postValue(retrofitPost.data)
                        }
                        is Result.Error -> {
                            loadingMessage.postValue(false)
                            errorMessage.postValue(retrofitPost.exception)
                        }
                    }

            } catch (e: Exception) {
                    errorMessage.postValue("Something Went Wrong")
            }
            }

        }

    }