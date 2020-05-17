package com.e.fabwithanmation.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "uris_table")
data class ImageUris(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id:Int,

    @ColumnInfo(name = "uri")
    var imageuri:String

)