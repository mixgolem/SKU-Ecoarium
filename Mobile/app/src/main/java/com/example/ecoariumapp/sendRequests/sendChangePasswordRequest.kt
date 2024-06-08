package com.example.ecoariumapp.sendRequests

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.IpConfig
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

// 비밀번호 변경 요청을 보내는 함수
public fun sendChangePasswordRequest(
    fragment: Fragment,
    present_pw: String,
    new_pw: String,
    new_pw_verification: String
) {
    // 변경할 비밀번호 정보를 JSON 형태로 변환
    val json = JSONObject()
    json.put("present_pw", present_pw)
    json.put("new_pw", new_pw)
    json.put("new_pw_verification", new_pw_verification)

    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val body = json.toString().toRequestBody(mediaType)

    // POST 요청 생성
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/auth/changePwMobile")
        .post(body)
        .build()

    // 요청을 보내고 응답을 처리
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            val responseBody = response.body?.string()
            Log.d("ChangePasswordFragment", "response: $responseBody")
            // 응답에 따른 UI 업데이트
            fragment.activity?.runOnUiThread {
                when (responseBody) {
                    "1" -> Toast.makeText(fragment.context, "새 비밀번호 확인 불일치", Toast.LENGTH_SHORT).show()
                    "2" -> Toast.makeText(fragment.context, "현재 비밀번호 불일치", Toast.LENGTH_SHORT).show()
                    "3" -> {
                        Toast.makeText(fragment.context, "비밀번호 변경 성공!", Toast.LENGTH_SHORT).show()
                        // 비밀번호 변경 성공시 로그아웃 요청
                        sendLogoutRequest(fragment)
                    }
                }
            }
        }
    })
}