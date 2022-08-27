package com.example.wardrobe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wardrobe.dao.BottomImageDao
import com.example.wardrobe.model.BottomImageModel

@Database(entities = [BottomImageModel::class], version = 1, exportSchema = false)
abstract class BottomImageDatabase:RoomDatabase() {
    abstract fun bottomImageDao():BottomImageDao
    companion object {
        private var INSTANCE: BottomImageDatabase? = null
        fun getInstance(context: Context): BottomImageDatabase? {
            if (INSTANCE == null) {
                synchronized(BottomImageDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BottomImageDatabase::class.java, "bottomImage.db"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}