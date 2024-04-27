package com.example.ecoariumapp.sendRequest

import android.app.Activity
import android.content.Intent
import com.example.ecoariumapp.MainActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

// 프로필 불러오기 요청
fun sendProfileRequest(activity: Activity, callback: Callback) {
    val request = Request.Builder()
        .url("http://192.168.94.42:8000/load-profile")
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

// 전체 기록 불러오기 요청
fun sendAllLogsRequest(activity: Activity, callback: Callback) {
    val request = Request.Builder()
        .url("http://192.168.94.42:8000/load-all-logs")
        .get()
        .build()

    client.newCall(request).enqueue(callback)
}

// 획득 기록 불러오기 요청
fun sendEarningsLogsRequest(activity: Activity, callback: Callback) {
    val request = Request.Builder()
        .url("http://192.168.94.42:8000/load-earnings-logs")
        .get()
        .build()

    client.newCall(request).enqueue(callback)
}

// 사용 기록 불러오기 요청
fun sendUsagesLogsRequest(activity: Activity, callback: Callback) {
    val request = Request.Builder()
        .url("http://192.168.94.42:8000/load-usages-logs")
        .get()
        .build()

    client.newCall(request).enqueue(callback)
}