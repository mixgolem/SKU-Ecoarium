package com.example.ecoariumapp.sendRequests

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.LoginActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

public fun sendDeleteRequest(fragment: Fragment,password: String) {
    val json = JSONObject()
    json.put("present_pw", password)

    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val body = json.toString().toRequestBody(mediaType)

    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/auth/withdrawal")
        .post(body)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            Log.d("DeleteAccountFragment", "response: $response")
            val responseBody = response.body?.string()
            if (responseBody == "true") {
                // 삭제 성공시 처리
                fragment.activity?.runOnUiThread {
                    // 세션 끊기
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
                // 삭제 실패 처리
                fragment.activity?.runOnUiThread {
                    Toast.makeText(fragment.context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    })
}