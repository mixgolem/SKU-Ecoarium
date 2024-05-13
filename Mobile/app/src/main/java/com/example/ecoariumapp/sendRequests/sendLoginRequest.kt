package com.example.ecoariumapp.sendRequests

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.activities.MainActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

// 쿠키를 관리하는 클래스
public class MyCookieJar : CookieJar {
    private val cookies = mutableListOf<Cookie>()

    // 서버로부터 받은 쿠키를 저장
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        this.cookies.addAll(cookies)
    }

    // 요청을 보낼 때 쿠키를 반환
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookies
    }

    // 쿠키를 모두 제거하는 함수
    fun clear() {
        cookies.clear()
    }
}

// 쿠키를 관리하는 OkHttpClient 인스턴스 생성
val cookieJar = MyCookieJar()

// 로그인 요청을 보내는 함수
val client = OkHttpClient.Builder()
    .cookieJar(cookieJar)
    .addInterceptor { chain ->
        val originalRequest = chain.request()
        val cookies = cookieJar.loadForRequest(originalRequest.url)
        val builder = originalRequest.newBuilder()
        val cookieHeader = StringBuilder()

        // 쿠키를 요청 헤더에 추가
        for (cookie in cookies) {
            cookieHeader.append("${cookie.name}=${cookie.value}; ")
        }
        builder.addHeader("Cookie", cookieHeader.toString())
        val requestWithCookies = builder.build()
        chain.proceed(requestWithCookies)
    }
    .build()

public fun sendLoginRequest(activity: Activity, username: String, password: String) {
    // 로그인 정보를 JSON 형태로 변환

    val json = JSONObject()
    json.put("username", username)
    json.put("password", password)

    Log.d("LoginActivity", "로그인 정보: $json")
    // JSON을 RequestBody로 변환
    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val body = json.toString().toRequestBody(mediaType)

    // POST 요청 생성
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/auth/loginMobile")
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
            Log.d("LoginActivity", "서버로부터 받은 응답: $responseBody")
            // 로그인 성공 시 MainActivity로 이동
            if (responseBody == "true") {
                Handler(Looper.getMainLooper()).post {
                    Log.d("LoginActivity", "Main 액티비티 실행 완료")
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()

                    // 로그인 상태 저장
                    val sharedPref = activity.getSharedPreferences("login", Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putBoolean("isLoggedIn", true)
                        apply()
                    }
                }
            }
            else {
                // 로그인 실패 시 토스트 메시지 표시
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(activity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    })
}