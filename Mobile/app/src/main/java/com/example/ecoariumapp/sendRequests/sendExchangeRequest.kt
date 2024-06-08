package com.example.ecoariumapp.sendRequests

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.ecoariumapp.IpConfig
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

// 아이템 교환 요청을 보내는 함수
fun sendExchangeRequest(context: Context, itemId: Int) {
    // 교환할 아이템의 ID를 JSON 형태로 변환
    val json = JSONObject()
    json.put("itemId", itemId)
    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val body = json.toString().toRequestBody(mediaType)

    // PUT 요청 생성
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/store/exchange")
        .put(body)
        .build()

    // 요청을 보내고 응답을 처리
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }
        override fun onResponse(call: Call, response: Response) {
            val responseBody = response.body?.string()
            // 교환 요청 실패 처리
            if (responseBody == "false") {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(context, "스탬프가 부족합니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    })
}