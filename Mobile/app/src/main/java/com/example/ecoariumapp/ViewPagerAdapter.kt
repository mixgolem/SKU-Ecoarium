package com.example.ecoariumapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter (eventList: ArrayList<Int>):RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>(){
    var item = eventList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount():Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.event.setImageResource(item[position])
        Log.d("ViewPagerAdapter", "Image resource ID at position $position: ${item[position]}")
    }
    inner class PagerViewHolder(parent: ViewGroup):RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.layout_event, parent, false)){
            val event = itemView.findViewById<ImageView>(R.id.imageView_event)
    }
}