package com.e.fabwithanmation.roomdb


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ImageUris::class], version = 1, exportSchema = false)
abstract class ImageUrisDatabase : RoomDatabase() {

    abstract fun imageUriDao(): ImageUriDao

    companion object {
        @Volatile
        private var INSTANCE: ImageUrisDatabase? = null

        fun getDatabase(context: Context): ImageUrisDatabase? {
            if (INSTANCE == null) {
                synchronized(ImageUrisDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            ImageUrisDatabase::class.java, "imageuri_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}