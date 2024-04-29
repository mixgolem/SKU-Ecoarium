package com.example.ecoariumapp.sendRequests

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.LoginActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

public fun sendLogoutRequest(fragment: Fragment) {
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/auth/logout")
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
                fragment.activity?.runOnUiThread {
                    // MainActivity로 이동
                    fragment.activity?.startActivity(Intent(fragment.activity, LoginActivity::class.java))
                    // HomeActivity 종료
                    fragment.activity?.finish()

                    // 로그인 상태 제거
                    val sharedPref = fragment.activity?.getSharedPreferences("login", Context.MODE_PRIVATE)
                    with (sharedPref!!.edit()) {
                        putBoolean("isLoggedIn", false)
                        apply()
                    }
                }
            }
        }
    })
}