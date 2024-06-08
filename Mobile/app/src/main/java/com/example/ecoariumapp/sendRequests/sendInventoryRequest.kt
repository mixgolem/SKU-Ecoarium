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

// 서버에서 사용 가능한 아이템 리스트를 가져오는 함수
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
            // 요청 성공 처리
            if (response.isSuccessful) {
                val responseBody: ResponseBody? = response.body
                val jsonData: String = responseBody?.string() ?: ""
                val jsonObject = JSONObject(jsonData)

                val availableArray = jsonObject.getJSONArray("available_items")
                val itemArray = jsonObject.getJSONArray("items")

                val availableList = mutableListOf<Triple<String, String, String>>()
                val itemList = mutableListOf<Triple<String, String, String>>()

                // 사용 가능한 아이템 정보 파싱
                for (i in 0 until availableArray.length()) {
                    val availableObject = availableArray.getJSONObject(i)
                    val itemId = availableObject.getString("itemId")
                    val createdAt = availableObject.getString("createdAt")
                    val code = availableObject.getString("code")
                    availableList.add(Triple(itemId, createdAt, code))
                }

                // 아이템 정보 파싱
                for (i in 0 until itemArray.length()) {
                    val itemObject = itemArray.getJSONObject(i)
                    val id = itemObject.getString("id")
                    val name = itemObject.getString("name")
                    var img = itemObject.getString("img")
                    img = img.substringAfter("/img/")
                    itemList.add(Triple(id, name, img))
                }

                // 사용 가능한 아이템과 아이템 정보를 결합
                val combinedList = mutableListOf<List<String>>()
                for (i in 0 until availableArray.length()) {
                    for (j in 0 until itemArray.length()) {
                        if(availableList[i].first == itemList[j].first){
                            combinedList.add(listOf(availableList[i].first, availableList[i].second, itemList[j].second, itemList[j].third, availableList[i].third))
                        }
                    }
                }
                Log.d("combinedList", "combinedList: $combinedList")

                // UI 업데이트
                fragment.activity?.runOnUiThread {
                    val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.inventoryRecyclerView)
                    recyclerView?.layoutManager = LinearLayoutManager(fragment.context)
                    recyclerView?.adapter = InventoryRecyclerViewAdapter(combinedList)
                }
            }
        }
    })
}

// 서버에서 완료된 아이템 리스트를 가져오는 함수
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
            // 요청 성공 처리
            if (response.isSuccessful) {
                val responseBody: ResponseBody? = response.body
                val jsonData: String = responseBody?.string() ?: ""
                val jsonObject = JSONObject(jsonData)

                val completedArray = jsonObject.getJSONArray("completed_items")
                val itemArray = jsonObject.getJSONArray("items")

                val completedList = mutableListOf<Triple<String, String,String>>()
                val itemList = mutableListOf<Triple<String, String, String>>()

                // 완료된 아이템 정보 파싱
                for (i in 0 until completedArray.length()) {
                    val completedObject = completedArray.getJSONObject(i)
                    val itemId = completedObject.getString("itemId")
                    val createdAt = completedObject.getString("createdAt")
                    val code = completedObject.getString("code")
                    completedList.add(Triple(itemId, createdAt, code))
                }

                // 아이템 정보 파싱
                for (i in 0 until itemArray.length()) {
                    val itemObject = itemArray.getJSONObject(i)
                    val id = itemObject.getString("id")
                    val name = itemObject.getString("name")
                    var img = itemObject.getString("img")
                    img = img.substringAfter("/img/")
                    itemList.add(Triple(id, name, img))
                }

                // 완료된 아이템과 아이템 정보를 결합
                val combinedList = mutableListOf<List<String>>()
                for (i in 0 until completedArray.length()) {
                    for (j in 0 until itemArray.length()) {
                        if(completedList[i].first == itemList[j].first){
                            combinedList.add(listOf(completedList[i].first, completedList[i].second, itemList[j].second, itemList[j].third, completedList[i].third))
                        }
                    }
                }
                Log.d("combinedList", "combinedList: $combinedList")

                // UI 업데이트
                fragment.activity?.runOnUiThread {
                    val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.inventoryRecyclerView)
                    recyclerView?.layoutManager = LinearLayoutManager(fragment.context)
                    recyclerView?.adapter = InventoryRecyclerViewAdapter(combinedList)
                }
            }
        }
    })
}