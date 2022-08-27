package com.example.wardrobe.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BottomImage")
data class BottomImageModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val imagePath: String
)
