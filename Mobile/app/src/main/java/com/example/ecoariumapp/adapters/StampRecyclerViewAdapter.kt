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
import org.json.JSONArray

class StampRecyclerViewAdapter(private val allLogs: JSONArray, private val items: JSONArray) :
    RecyclerView.Adapter<StampRecyclerViewAdapter.RecyclerViewHolder>() {
    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stampImage: ImageView = view.findViewById(R.id.stampImage)
        val stampName: TextView = view.findViewById(R.id.stampName)
        val stampMessage: TextView = view.findViewById(R.id.stampMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_stamp, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val log = allLogs.getJSONObject(position)

        // Check if the log JSONObject has the 'location' field
        val isStamp = log.has("location")

        // If it's a stamp, get the 'location' field. Otherwise, get the 'name' field.
        val stampName = if (isStamp) log.getString("location") else getItemName(log.getInt("itemId"))

        // If it's a stamp, get the 'createdAt' field. Otherwise, get the 'message' field.
        val stampMessage =log.optString("createdAt")

        // If it's a stamp, use the 'stamp' image from @drawable. Otherwise, get the 'img' field.
        val stampImage = if (isStamp) R.drawable.stamp else getItemImage(log.getInt("itemId"))

        // Set the image, name, and message to the views
        // Use Glide to load the image from a URL or resource, and use the 'stamp' image as the error fallback
        Glide.with(holder.stampImage.context)
            .load(stampImage)
            .error(R.drawable.stamp) // Use the 'stamp' image from @drawable as the error fallback
            .into(holder.stampImage)
        holder.stampName.text = stampName
        holder.stampMessage.text = stampMessage

        Log.d("StampRecyclerViewAdapter", "Stamp name: $stampName, Stamp message: $stampMessage")
    }

    private fun getItemName(itemId: Int): String {
        for (i in 0 until items.length()) {
            val item = items.getJSONObject(i)
            if (item.getInt("id") == itemId) {
                return item.getString("name")
            }
        }
        return ""
    }

    private fun getItemImage(itemId: Int): String {
        val serverUrl = "http://${IpConfig.serverIp}:8000/uploads/" // Replace with your server's URL
        for (i in 0 until items.length()) {
            val item = items.getJSONObject(i)
            if (item.getInt("id") == itemId) {
                val imagePath = item.getString("img")
                val imageFileName = imagePath.split("/").last() // Extract the file name from the path
                return "$serverUrl$imageFileName"
            }
        }
        return ""
    }

    override fun getItemCount() = allLogs.length()
}