package com.example.ecoariumapp.sendRequests

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.R
import com.example.ecoariumapp.adapters.InventoryRecyclerViewAdapter
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

// 서버에서 아이템 리스트 가져오기
fun sendAvailableItemRequest(fragment: Fragment) {
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/inventory/available")
        .get()
        .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }
        override fun onResponse(call: Call, response: Response) {
            // 프로필 요청 성공 처리
            if (response.isSuccessful) {
                val responseBody: ResponseBody? = response.body
                val jsonData: String = responseBody?.string() ?: ""

                val jsonObject = JSONObject(jsonData)
                val availableObject = jsonObject.getJSONArray("available_items")
                Log.d("availableobj", "available: $availableObject")
                val itemObject = jsonObject.getJSONArray("items")
                Log.d("itemobj", "item: $itemObject")

                val availableArray = jsonObject.getJSONArray("available_items")
                val itemArray = jsonObject.getJSONArray("items")

                val availableList = mutableListOf<Pair<String, String>>()
                val itemList = mutableListOf<Triple<String, String, String>>()

                for (i in 0 until availableArray.length()) {
                    val availableObject = availableArray.getJSONObject(i)
                    val itemId = availableObject.getString("itemId")
                    val createdAt = availableObject.getString("createdAt")
                    availableList.add(Pair(itemId, createdAt))
                }

                for (i in 0 until itemArray.length()) {
                    val itemObject = itemArray.getJSONObject(i)
                    val id = itemObject.getString("id")
                    val name = itemObject.getString("name")
                    var img = itemObject.getString("img")
                    img = img.substringAfter("/img/")
                    itemList.add(Triple(id, name, img))
                }

                val combinedList = mutableListOf<List<String>>()
                for (i in 0 until availableArray.length()) {
                    for (j in 0 until itemArray.length()) {
                        if(availableList[i].first == itemList[j].first){
                            combinedList.add(listOf(availableList[i].first, availableList[i].second, itemList[j].second, itemList[j].third))
                        }
                    }
                }
                Log.d("combinedList", "combinedList: $combinedList")

                fragment.activity?.runOnUiThread {
                    val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.inventoryRecyclerView)
                    recyclerView?.layoutManager = LinearLayoutManager(fragment.context)
                    recyclerView?.adapter = InventoryRecyclerViewAdapter(combinedList)
                }
            }
        }
    })
}
fun sendCompletedItemRequest(fragment: Fragment) {
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/inventory/completed")
        .get()
        .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }
        override fun onResponse(call: Call, response: Response) {
            // 프로필 요청 성공 처리
            if (response.isSuccessful) {
                val responseBody: ResponseBody? = response.body
                val jsonData: String = responseBody?.string() ?: ""

                val jsonObject = JSONObject(jsonData)
                val completedObject = jsonObject.getJSONArray("completed_items")
                Log.d("availableobj", "available: $completedObject")
                val itemObject = jsonObject.getJSONArray("items")
                Log.d("itemobj", "item: $itemObject")

                val completedArray = jsonObject.getJSONArray("completed_items")
                val itemArray = jsonObject.getJSONArray("items")

                val completedList = mutableListOf<Pair<String, String>>()
                val itemList = mutableListOf<Triple<String, String, String>>()

                for (i in 0 until completedArray.length()) {
                    val completedObject = completedArray.getJSONObject(i)
                    val itemId = completedObject.getString("itemId")
                    val createdAt = completedObject.getString("createdAt")
                    completedList.add(Pair(itemId, createdAt))
                }

                for (i in 0 until itemArray.length()) {
                    val itemObject = itemArray.getJSONObject(i)
                    val id = itemObject.getString("id")
                    val name = itemObject.getString("name")
                    var img = itemObject.getString("img")
                    img = img.substringAfter("/img/")
                    itemList.add(Triple(id, name, img))
                }

                val combinedList = mutableListOf<List<String>>()
                for (i in 0 until completedArray.length()) {
                    for (j in 0 until itemArray.length()) {
                        if(completedList[i].first == itemList[j].first){
                            combinedList.add(listOf(completedList[i].first, completedList[i].second, itemList[j].second, itemList[j].third))
                        }
                    }
                }
                Log.d("combinedList", "combinedList: $combinedList")

                fragment.activity?.runOnUiThread {
                    val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.inventoryRecyclerView)
                    recyclerView?.layoutManager = LinearLayoutManager(fragment.context)
                    recyclerView?.adapter = InventoryRecyclerViewAdapter(combinedList)
                }
            }
        }
    })
}