package com.e.fabwithanmation.roomdb

import android.app.Application
import android.content.Context
import com.e.fabwithanmation.roomdb.ImageUris
import com.e.fabwithanmation.roomdb.ImageUriDao
import com.e.fabwithanmation.roomdb.ImageUrisDatabase
import kotlinx.coroutines.*

import kotlin.coroutines.CoroutineContext


class ImageuriRepository(context: Context) : CoroutineScope{

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private var imageUriDao: ImageUriDao?

    init {
        val db = ImageUrisDatabase.getDatabase(context)
        imageUriDao = db?.imageUriDao()
    }

    fun getImageuris() = imageUriDao?.getImageUris()

    fun setImageuri(imageuri: ImageUris) {
            launch  { setImageuriBG(imageuri) }
    }

    private suspend fun setImageuriBG(imageuri: ImageUris){
       withContext(Dispatchers.IO){
           imageUriDao?.setImageUri(imageuri)
       }
    }

}
