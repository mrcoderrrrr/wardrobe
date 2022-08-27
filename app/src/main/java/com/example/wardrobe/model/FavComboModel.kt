package com.example.wardrobe.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "FavCombo")
data class FavComboModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val isFav:Int,
    val imagePathTop: String,
    val imagePathBottom: String
)
