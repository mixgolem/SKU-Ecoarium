package com.example.ecoariumapp.adapters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.R
import com.example.ecoariumapp.sendRequests.sendExchangeRequest

// 상점 아이템을 표시하는 RecyclerView 어댑터
class StoreRecyclerViewAdapter(private val itemList: List<List<String>>) :
    RecyclerView.Adapter<StoreRecyclerViewAdapter.RecyclerViewHolder>() {

    // 뷰 홀더 정의
    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val exchangeButton: Button = view.findViewById(R.id.exchangeButton)
        val ItemImage: ImageView = view.findViewById(R.id.itemImage)
        val ItemName: TextView = view.findViewById(R.id.itemName)
        val ItemPrice: TextView = view.findViewById(R.id.itemPrice)
    }

    // 뷰 홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_store_item, parent, false)
        return RecyclerViewHolder(view)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val itemData = itemList[position]

        // 아이템 가격 설정
        val itemPrice = "${itemData[3]}개"
        holder.ItemPrice.text = itemPrice

        // 아이템 이름 설정
        val itemName = itemData[1]
        holder.ItemName.text = itemName

        // 아이템 이미지 설정
        val itemImageFileName = itemData[2]
        val itemImageURL = "http://${IpConfig.serverIp}:8000/uploads/$itemImageFileName"
        Glide.with(holder.ItemImage.context)
            .load(itemImageURL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Log.e("Glide", "Image load failed", e)
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    Log.d("Glide", "Image load success")
                    return false
                }
            })
            .into(holder.ItemImage)

        // 교환 버튼 클릭 이벤트 핸들러 설정
        holder.exchangeButton.setOnClickListener { view ->
            val builder = AlertDialog.Builder(view.context)
            builder.setTitle("교환 확인")
            builder.setMessage("해당 상품을 교환하시겠습니까?")
            builder.setPositiveButton("예") { dialog, which ->
                sendExchangeRequest(view.context, itemList[position][0].toInt())
            }
            builder.setNegativeButton("아니오") { dialog, which ->
            }
            builder.show()
        }
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int {
        return itemList.size
    }
}