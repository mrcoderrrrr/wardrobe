package com.example.wardrobe.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.wardrobe.R
import com.example.wardrobe.adapter.ViewPagerAdapterFavourite
import com.example.wardrobe.adapter.ViewPagerAdapterTop
import com.example.wardrobe.database.FavComboDatabase
import com.example.wardrobe.databinding.ActivityFavouriteBinding
import com.example.wardrobe.model.FavComboModel

class FavouriteActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityFavouriteBinding
    var viewPagerAdapterFavourite: ViewPagerAdapterFavourite? = null
    var imageFav = ArrayList<FavComboModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_favourite)
        viewPagerFav()
    }

    private fun viewPagerFav() {
        imageFav = ArrayList()
        imageFav = FavComboDatabase.getInstance(this)?.favComboDao()!!
            .userViewData() as ArrayList<FavComboModel>
        viewPagerAdapterFavourite = ViewPagerAdapterFavourite(this, imageFav)
        dataBinding.vFav.adapter = viewPagerAdapterFavourite
        viewPagerAdapterFavourite!!.notifyDataSetChanged()
    }
}