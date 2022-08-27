package com.example.wardrobe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wardrobe.dao.TopImageDao
import com.example.wardrobe.model.TopImageModel

@Database(entities = [TopImageModel::class], version = 1, exportSchema = false)
abstract class TopImageDatabase :RoomDatabase(){
abstract fun topImageDao(): TopImageDao

    companion object {
        private var INSTANCE: TopImageDatabase? = null
        fun getInstance(context: Context): TopImageDatabase? {
            if (INSTANCE == null) {
                synchronized(TopImageDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TopImageDatabase::class.java, "topImage.db"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}