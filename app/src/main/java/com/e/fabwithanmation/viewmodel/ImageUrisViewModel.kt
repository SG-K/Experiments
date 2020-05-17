package com.e.fabwithanmation.viewmodel

import android.app.Application
import android.view.animation.Animation

import androidx.lifecycle.AndroidViewModel
import com.e.fabwithanmation.roomdb.ImageUris
import com.e.fabwithanmation.roomdb.ImageuriRepository


class ImageUrisViewModel (application: Application) : AndroidViewModel(application) {

    //region variables
    var fab_open: Animation? = null
    var fab_close: Animation? = null
    var fab_clock: Animation? = null
    var fab_anticlock: Animation? = null
    var isOpen = false
    val RES_IMAGE = 100
    val CAMERA_IMAGE = 110
   //endregion

    //repository
    private var repository:ImageuriRepository = ImageuriRepository(application)

    fun getImageUrs() = repository.getImageuris()

    fun setImageUri(imageUri: ImageUris) {
        repository.setImageuri(imageUri)
    }

}