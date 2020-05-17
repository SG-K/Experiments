package com.e.fabwithanmation.roomdb

import android.app.Application
import com.e.fabwithanmation.roomdb.ImageUris
import com.e.fabwithanmation.roomdb.ImageUriDao
import com.e.fabwithanmation.roomdb.ImageUrisDatabase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class ImageuriRepository(application: Application) : CoroutineScope{

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var imageUriDao: ImageUriDao?

    init {
        val db = ImageUrisDatabase.getDatabase(application)
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
