package com.example.wardrobe.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.wardrobe.model.FavComboModel

@Dao
interface FavComboDao {
    @Insert
    fun userInsert(favComboModel: FavComboModel)

    @Query("SELECT * FROM favcombo")
    fun userViewData():List<FavComboModel>

}