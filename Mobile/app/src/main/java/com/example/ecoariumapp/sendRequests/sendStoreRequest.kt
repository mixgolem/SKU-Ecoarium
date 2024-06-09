package com.example.ecoariumapp.sendRequests

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.R
import com.example.ecoariumapp.adapters.StoreRecyclerViewAdapter
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

// 서버에서 아이템 리스트를 가져오는 함수
fun sendLoadItemsRequest(fragment: Fragment) {
    // 요청 객체 생성
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/store/load")
        .get()
        .build()
    // 요청 전송
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
                val itemArray = JSONArray(jsonData)
                val itemList = mutableListOf<List<String>>()

                // 아이템 정보 파싱
                for (i in 0 until itemArray.length()) {
                    val itemObject = itemArray.getJSONObject(i)
                    val id = itemObject.getString("id")
                    val name = itemObject.getString("name")
                    var img = itemObject.getString("img")
                    val price = itemObject.getString("price")
                    img = img.substringAfter("/img/")
                    itemList.add(listOf(id, name, img, price))
                }

                Log.d("itemList", "itemList: $itemList")

                // UI 업데이트
                fragment.activity?.runOnUiThread {
                    val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.storeRecyclerView)
                    recyclerView?.layoutManager = LinearLayoutManager(fragment.context)
                    recyclerView?.adapter = StoreRecyclerViewAdapter(itemList)
                }
            }
        }
    })
}