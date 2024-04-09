package com.example.ecoariumapp

import android.app.Activity
import android.content.Intent
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

public fun sendLogoutRequest(activity: Activity) {
    val request = Request.Builder()
        .url("http://192.168.93.42:8000/auth/logout")
        .get()
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            // 로그아웃 요청 성공 처리
            if (response.isSuccessful) {
                // 쿠키 제거
                cookieJar.clear()
                activity.runOnUiThread {
                    // MainActivity로 이동
                    activity.startActivity(Intent(activity, MainActivity::class.java))
                    // HomeActivity 종료
                    activity.finish()
                }
            }
        }
    })
}