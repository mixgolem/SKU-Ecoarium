package com.example.ecoariumapp.sendRequests

import androidx.fragment.app.Fragment
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.ProfileFragment
import com.example.ecoariumapp.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

// 프로필 수정 요청 보내기
fun sendModifyRequest(fragment: Fragment, nickname: String) {
    // JSON 객체 생성
    val json = JSONObject()
    json.put("nickname", nickname)

    // 미디어 타입 설정
    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val body = json.toString().toRequestBody(mediaType)

    // 요청 객체 생성
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/auth/modify")
        .put(body)
        .build()

    // 요청 전송
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            println("원본 = " + response.body)
            // 요청 성공 처리
            if (response.isSuccessful) {
                // UI 업데이트는 메인 스레드에서 수행해야 하므로 runOnUiThread를 사용
                fragment.activity?.runOnUiThread {
                    val transaction = fragment.parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainer, ProfileFragment())
                    transaction.commit()
                }
            }
        }
    })
}