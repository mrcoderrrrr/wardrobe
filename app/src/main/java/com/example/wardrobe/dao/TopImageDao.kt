package com.example.wardrobe.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.wardrobe.model.TopImageModel
@Dao
interface TopImageDao {
    @Insert
    fun userInsert(topImageModel: TopImageModel)

    @Query("SELECT * FROM topImage")
    fun userViewData():List<TopImageModel>
}