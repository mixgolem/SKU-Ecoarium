package com.example.ecoariumapp.sendRequests

import android.util.Log
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

// 서버에서 스택 요청 보내기
fun sendStacksRequest(fragment: Fragment) {
    // 요청 객체 생성
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/main/loadPlasticStacks")
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
                val jsonObject = JSONObject(jsonData)

                val allStacks = jsonObject.getInt("allStacks")
                val myStacks = jsonObject.getInt("myStacks")

                Log.d("MypageFragment", "allStacks: $allStacks, myStacks: $myStacks")

                val plasticTextView = fragment.view?.findViewById<TextView>(R.id.plasticIntTextView)
                val plasticsTextView = fragment.view?.findViewById<TextView>(R.id.plasticsIntTextView)

                // UI 스레드에서 텍스트 뷰 업데이트
                fragment.activity?.runOnUiThread {
                    plasticTextView?.text = fragment.getString(R.string.stack, myStacks)
                    plasticsTextView?.text = fragment.getString(R.string.stack, allStacks)
                }
            }
        }
    })
}