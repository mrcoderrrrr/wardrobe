package com.example.wardrobe.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.wardrobe.model.BottomImageModel

@Dao
interface BottomImageDao {
    @Insert
    fun userInsert(bottomImageModel: BottomImageModel)

    @Query("SELECT * FROM bottomimage")
    fun userViewData():List<BottomImageModel>
}