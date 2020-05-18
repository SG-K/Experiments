package com.e.fabwithanmation.viewmodel

import android.view.animation.Animation
import androidx.lifecycle.ViewModel

class ImageUrisViewModel : ViewModel() {

    //region variables
    var fab_open: Animation? = null
    var fab_close: Animation? = null
    var fab_clock: Animation? = null
    var fab_anticlock: Animation? = null
    var isOpen = false
    val RES_IMAGE = 100
    val CAMERA_IMAGE = 110
   //endregion

}