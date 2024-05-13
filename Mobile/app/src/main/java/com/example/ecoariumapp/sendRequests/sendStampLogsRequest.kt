package com.example.ecoariumapp.sendRequests

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.R
import com.example.ecoariumapp.adapters.StampRecyclerViewAdapter
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

// 서버에서 아이템 리스트 가져오기
fun sendAllLogsRequest(fragment: Fragment) {
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/mypage/load-all-logs")
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
                val allLogsArray = jsonObject.getJSONArray("all_logs")
                val itemsArray = jsonObject.getJSONArray("items")

                // Create the adapter
                val adapter = StampRecyclerViewAdapter(allLogsArray, itemsArray)

                // Run on the UI thread to update the RecyclerView
                fragment.activity?.runOnUiThread {
                    val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.stampRecyclerView)
                    recyclerView?.adapter = adapter
                }
            }
        }
    })
}

fun sendEarnLogsRequest(fragment: Fragment) {
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/mypage/load-earnings-logs")
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

                Log.d("usageLogs", jsonData)

                val jsonObject = JSONObject(jsonData)
                val pointEarningArray = jsonObject.getJSONArray("point_earnings")
                val itemsArray = jsonObject.getJSONArray("items")

                // Create the adapter
                val adapter = StampRecyclerViewAdapter(pointEarningArray, itemsArray)

                // Run on the UI thread to update the RecyclerView
                fragment.activity?.runOnUiThread {
                    val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.stampRecyclerView)
                    recyclerView?.adapter = adapter
                }
            }
        }
    })
}

fun sendUsageLogsRequest(fragment: Fragment) {
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/mypage/load-usages-logs")
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
                Log.d("usageLogs", jsonData)

                val jsonObject = JSONObject(jsonData)
                val pointUsageArray = jsonObject.getJSONArray("point_usages")
                val itemsArray = jsonObject.getJSONArray("items")

                // Filter the logs to include only the used items
                val usedItemsArray = JSONArray()
                for (i in 0 until pointUsageArray.length()) {
                    val log = pointUsageArray.getJSONObject(i)
                    if (log.optString("delatedAt") != "") {
                        usedItemsArray.put(log)
                    }
                }

                // Create the adapter with the used items
                val adapter = StampRecyclerViewAdapter(usedItemsArray, itemsArray)

                // Run on the UI thread to update the RecyclerView
                fragment.activity?.runOnUiThread {
                    val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.stampRecyclerView)
                    recyclerView?.adapter = adapter
                }
            }
        }
    })
}