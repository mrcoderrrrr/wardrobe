package com.example.wardrobe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wardrobe.R
import com.example.wardrobe.databinding.ItemFavViewpagerBinding
import com.example.wardrobe.model.FavComboModel
import com.squareup.picasso.Picasso

class ViewPagerAdapterFavourite(var context: Context, var images: ArrayList<FavComboModel>):RecyclerView.Adapter<ViewPagerAdapterFavourite.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val dataBinding:ItemFavViewpagerBinding=ItemFavViewpagerBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_fav_viewpager,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        Picasso.get()
            .load(images[position].imagePathTop)
            .centerCrop()
            .resize(300,300)
            .into(holder.dataBinding.ivTop)
        Picasso.get()
            .load(images[position].imagePathBottom)
            .centerCrop()
            .resize(300,300)
            .into(holder.dataBinding.ivBottom)
    }

    override fun getItemCount(): Int {
      return images.size
    }
}