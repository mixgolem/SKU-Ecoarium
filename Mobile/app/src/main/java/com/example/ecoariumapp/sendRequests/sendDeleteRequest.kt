package com.example.ecoariumapp.sendRequests

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.activities.LoginActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

// 계정 삭제 요청을 보내는 함수
public fun sendDeleteRequest(fragment: Fragment,password: String) {
    // 삭제할 계정의 비밀번호를 JSON 형태로 변환
    val json = JSONObject()
    json.put("present_pw", password)

    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val body = json.toString().toRequestBody(mediaType)

    // POST 요청 생성
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/auth/withdrawalMobile")
        .post(body)
        .build()

    // 요청을 보내고 응답을 처리
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            Log.d("DeleteAccountFragment", "response: $response")
            val responseBody = response.body?.string()
            // 계정 삭제 성공 처리
            if (responseBody == "true") {
                fragment.activity?.runOnUiThread {
                    // 세션 종료
                    val sharedPreferences = fragment.activity?.getSharedPreferences("user_session", Context.MODE_PRIVATE)
                    val editor = sharedPreferences?.edit()
                    editor?.clear()
                    editor?.apply()
                    cookieJar.clear()

                    // LoginActivity로 이동
                    val intent = Intent(fragment.context, LoginActivity::class.java)
                    fragment.startActivity(intent)
                    fragment.activity?.finish()
                }
            } else {
                // 계정 삭제 실패 처리
                fragment.activity?.runOnUiThread {
                    Toast.makeText(fragment.context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    })
}