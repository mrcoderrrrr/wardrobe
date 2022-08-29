package com.example.wardrobe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wardrobe.R
import com.example.wardrobe.databinding.ItemTopViewpagerBinding
import com.example.wardrobe.model.TopImageModel
import com.squareup.picasso.Picasso

class ViewPagerAdapterTop(var context:Context, private var images: ArrayList<TopImageModel>):RecyclerView.Adapter<ViewPagerAdapterTop.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dataBinding:ItemTopViewpagerBinding=ItemTopViewpagerBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_top_viewpager, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        Picasso.get()
            .load(images[position].imagePath)
            .centerCrop()
            .resize(300,300)
            .into(holder.dataBinding.ivTop)
    }
    override fun getItemCount(): Int {
       return images.size
    }
}