package com.example.ecoariumapp.sendRequests

import com.example.ecoariumapp.IpConfig
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

// 서버에서 아이템 리스트 가져오기
fun sendExchangeRequest(itemId: Int) {
    val json = JSONObject()
    json.put("itemId", itemId)
    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val body = json.toString().toRequestBody(mediaType)

    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/store/exchange")
        .put(body)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }
        override fun onResponse(call: Call, response: Response) {
            // 프로필 요청 성공 처리
            if (response.isSuccessful) {

            }
        }
    })
}