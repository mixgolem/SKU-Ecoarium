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

class InventoryRecyclerViewAdapter(private val itemList: List<List<String>>) :
    RecyclerView.Adapter<InventoryRecyclerViewAdapter.RecyclerViewHolder>() {

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ItemImage: ImageView = view.findViewById(R.id.stampImage)
        val ItemName: TextView = view.findViewById(R.id.stampName)
        val ItemMessage: TextView = view.findViewById(R.id.stampMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_inventory_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val itemData = itemList[position]
        Log.d("InventoryRecyclerViewAdapter", "Item data: $itemData")

        // Assuming that the second element in the list is the item message
        val itemMessage = itemData[1]
        holder.ItemMessage.text = convertIsoToCustomFormat(itemMessage)

        // Assuming that the third element in the list is the item name
        val itemName = itemData[2]
        holder.ItemName.text = itemName

        // Assuming that the fourth element in the list is the item image file name
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
                // ImageView에 대한 상단 마진을 설정합니다. 여기서는 30dp의 마진을 추가하였습니다.
                val marginTopInDp = 30
                val marginBottomInDp = 20
                val marginTopInPixels = (marginTopInDp * holder.itemView.context.resources.displayMetrics.density).toInt()
                val marginBottomInPixels = (marginBottomInDp * holder.itemView.context.resources.displayMetrics.density).toInt()
                imageViewLayoutParams.setMargins(0, marginTopInPixels, 0, marginBottomInPixels)
                imageView.layoutParams = imageViewLayoutParams

                // 바코드 번호를 표시하는 TextView를 생성합니다.
                val textView = TextView(holder.itemView.context)
                textView.text = itemCode  // 바코드 번호를 설정합니다.
                textView.textSize = 18f  // 텍스트 크기를 설정합니다.
                textView.gravity = Gravity.CENTER  // 텍스트를 가운데 정렬합니다.

                val linearLayout = LinearLayout(holder.itemView.context)
                linearLayout.orientation = LinearLayout.VERTICAL  // 추가된 부분
                linearLayout.gravity = Gravity.CENTER
                linearLayout.addView(imageView)
                linearLayout.addView(textView)  // TextView를 LinearLayout에 추가합니다.

                val builder = AlertDialog.Builder(holder.itemView.context)
                builder.setView(linearLayout)
                builder.setPositiveButton("확인", null)
                builder.show()
            } catch (e: WriterException) {
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}