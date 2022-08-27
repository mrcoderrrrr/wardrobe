package com.example.wardrobe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wardrobe.dao.FavComboDao
import com.example.wardrobe.model.FavComboModel

@Database(entities = [FavComboModel::class], version = 1, exportSchema = false)
abstract class FavComboDatabase: RoomDatabase() {
    abstract fun favComboDao(): FavComboDao

    companion object {
        private var INSTANCE: FavComboDatabase? = null
        fun getInstance(context: Context): FavComboDatabase? {
            if (INSTANCE == null) {
                synchronized(FavComboDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavComboDatabase::class.java, "favCombo.db"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}