package com.example.ecoariumapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.R
import com.example.ecoariumapp.convertIsoToCustomFormat
import org.json.JSONArray

// 스탬프를 표시하는 RecyclerView 어댑터
class StampRecyclerViewAdapter(private val allLogs: JSONArray, private val items: JSONArray) :
    RecyclerView.Adapter<StampRecyclerViewAdapter.RecyclerViewHolder>() {

    // 뷰 홀더 정의
    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stampImage: ImageView = view.findViewById(R.id.itemImage)
        val stampName: TextView = view.findViewById(R.id.itemName)
        val stampMessage: TextView = view.findViewById(R.id.itemPrice)
    }

    // 뷰 홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_stamp, parent, false)
        return RecyclerViewHolder(view)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val log = allLogs.getJSONObject(position)

        var isLocation: Boolean = log.has("location")
        var isType: Boolean = log.has("type")

        // 스탬프 이름 설정
        val stampName =
            if (isType) {
                if (log.getString("type") == "point_earning") {
                    log.getString("detail")
                } else {
                    getItemName(log.getInt("detail"))
                }
            } else {
                if (isLocation) {
                    log.getString("location")
                } else {
                    getItemName(log.getInt("itemId"))
                }
            }

        // 스탬프 메시지 설정
        val stampMessage = convertIsoToCustomFormat(log.optString("createdAt"))

        // 스탬프 이미지 설정
        var stampImage =
            if (isType) {
                if (log.getString("type") == "point_earning") {
                    R.drawable.stamp
                } else {
                    getItemImage(log.getInt("detail"))
                }
            } else {
                if (isLocation) {
                    R.drawable.stamp
                } else {
                    getItemImage(log.getInt("itemId"))
                }
            }

        // 뷰에 이미지, 이름, 메시지 설정
        Glide.with(holder.stampImage.context)
            .load(stampImage)
            .error(R.drawable.stamp) // 에러 발생 시 기본 스탬프 이미지 사용
            .into(holder.stampImage)
        holder.stampName.text = stampName
        holder.stampMessage.text = stampMessage
    }

    // 아이템 이름 가져오기
    private fun getItemName(itemId: Int): String {
        for (i in 0 until items.length()) {
            val item = items.getJSONObject(i)
            if (item.getInt("id") == itemId) {
                return item.getString("name")
            }
        }
        return ""
    }

    // 아이템 이미지 가져오기
    private fun getItemImage(itemId: Int): String {
        val serverUrl = "http://${IpConfig.serverIp}:8000/uploads/"
        for (i in 0 until items.length()) {
            val item = items.getJSONObject(i)
            if (item.getInt("id") == itemId) {
                val imagePath = item.getString("img")
                val imageFileName = imagePath.split("/").last()
                return "$serverUrl$imageFileName"
            }
        }
        return ""
    }

    // 아이템 개수 반환
    override fun getItemCount() = allLogs.length()
}