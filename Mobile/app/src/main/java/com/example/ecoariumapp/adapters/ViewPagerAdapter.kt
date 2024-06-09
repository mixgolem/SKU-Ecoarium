package com.example.ecoariumapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoariumapp.R

// 이벤트 리스트를 표시하는 뷰페이저 어댑터
class ViewPagerAdapter (eventList: ArrayList<Int>):RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>(){
    var item = eventList

    // 뷰 홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    // 아이템 개수 반환
    override fun getItemCount():Int = item.size

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.event.setImageResource(item[position])
    }

    // 뷰 홀더 정의
    inner class PagerViewHolder(parent: ViewGroup):RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.layout_event, parent, false)){
        val event = itemView.findViewById<ImageView>(R.id.imageView_event)
    }
}