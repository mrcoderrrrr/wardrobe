package com.example.wardrobe.model

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TopImage")
data class TopImageModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val imagePath: String
)
