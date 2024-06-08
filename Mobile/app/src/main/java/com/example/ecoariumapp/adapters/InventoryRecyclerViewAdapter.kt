package com.example.ecoariumapp.adapters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.R
import com.example.ecoariumapp.convertIsoToCustomFormat
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

// 인벤토리 아이템을 표시하는 RecyclerView 어댑터
class InventoryRecyclerViewAdapter(private val itemList: List<List<String>>) :
    RecyclerView.Adapter<InventoryRecyclerViewAdapter.RecyclerViewHolder>() {

    // 뷰 홀더 정의
    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ItemImage: ImageView = view.findViewById(R.id.stampImage)
        val ItemName: TextView = view.findViewById(R.id.stampName)
        val ItemMessage: TextView = view.findViewById(R.id.stampMessage)
    }

    // 뷰 홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_inventory_item, parent, false)
        return RecyclerViewHolder(view)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val itemData = itemList[position]

        // 아이템 메시지 설정
        val itemMessage = itemData[1]
        holder.ItemMessage.text = convertIsoToCustomFormat(itemMessage)

        // 아이템 이름 설정
        val itemName = itemData[2]
        holder.ItemName.text = itemName

        // 아이템 이미지 설정
        val itemImageFileName = itemData[3]
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

        // 아이템 코드 설정 및 클릭 이벤트 핸들러 설정
        val itemCode = itemData[4]
        holder.itemView.setOnClickListener {
            val multiFormatWriter = MultiFormatWriter()
            try {
                val bitMatrix: BitMatrix = multiFormatWriter.encode(itemCode, BarcodeFormat.CODE_128,1000,400)
                val barcodeEncoder = BarcodeEncoder()
                val bitmap = barcodeEncoder.createBitmap(bitMatrix)

                val imageView = ImageView(holder.itemView.context)
                imageView.setImageBitmap(bitmap)

                val imageViewLayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val marginTopInDp = 30
                val marginBottomInDp = 20
                val marginTopInPixels = (marginTopInDp * holder.itemView.context.resources.displayMetrics.density).toInt()
                val marginBottomInPixels = (marginBottomInDp * holder.itemView.context.resources.displayMetrics.density).toInt()
                imageViewLayoutParams.setMargins(0, marginTopInPixels, 0, marginBottomInPixels)
                imageView.layoutParams = imageViewLayoutParams

                val textView = TextView(holder.itemView.context)
                textView.text = itemCode
                textView.textSize = 18f
                textView.gravity = Gravity.CENTER

                val linearLayout = LinearLayout(holder.itemView.context)
                linearLayout.orientation = LinearLayout.VERTICAL
                linearLayout.gravity = Gravity.CENTER
                linearLayout.addView(imageView)
                linearLayout.addView(textView)

                val builder = AlertDialog.Builder(holder.itemView.context)
                builder.setView(linearLayout)
                builder.setPositiveButton("확인", null)
                builder.show()
            } catch (e: WriterException) {
                e.printStackTrace()
            }
        }
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int {
        return itemList.size
    }
}