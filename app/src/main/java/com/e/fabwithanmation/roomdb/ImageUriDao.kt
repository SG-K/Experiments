package com.e.fabwithanmation.roomdb

import android.app.Notification


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



@Dao
interface ImageUriDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setImageUri(image: ImageUris)

    @Query("SELECT * from images_table ORDER BY id ASC")
    fun getImageUris() : LiveData<List<ImageUris>>

//    @Query("DELETE FROM uris_table")
//    fun deleteAll()

}