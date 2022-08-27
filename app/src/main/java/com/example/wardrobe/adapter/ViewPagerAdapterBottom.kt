package com.example.wardrobe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wardrobe.R
import com.example.wardrobe.databinding.ItemBottomViewpagerBinding
import com.example.wardrobe.model.BottomImageModel
import com.squareup.picasso.Picasso

class ViewPagerAdapterBottom(var context:Context, var images: ArrayList<BottomImageModel>):RecyclerView.Adapter<ViewPagerAdapterBottom.ViewPagerViewHolder>() {
    class ViewPagerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val dataBinding:ItemBottomViewpagerBinding=ItemBottomViewpagerBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_bottom_viewpager,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        Picasso.get()
            .load(images.get(position).imagePath)
            .centerCrop()
            .resize(300,300)
            .into(holder.dataBinding.ivBottom)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}