package com.example.ecoariumapp.sendRequests

import android.util.Log
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

// 프로필 불러오기 요청
fun sendProfileRequest(fragment: Fragment) {
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/auth/loadProfile")
        .get()
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            // 프로필 요청 성공 처리
            if (response.isSuccessful) {
                val responseBody: ResponseBody? = response.body
                val jsonData: String = responseBody?.string() ?: ""

                val jsonObject = JSONObject(jsonData)
                val userObject = jsonObject.getJSONObject("user")   //user
                Log.d("ProfileFragment", "서버로부터 받은 응답: $userObject")

                val id = userObject.getString("username")           //id
                val nickname = userObject.getString("nickname")     //nickname
                val email = userObject.getString("email")           //email

                val idTextView = fragment.view?.findViewById<TextView>(R.id.idTextView)
                val emailTextView = fragment.view?.findViewById<TextView>(R.id.emailTextView)
                val nicknameTextView = fragment.view?.findViewById<TextView>(R.id.nicknameTextView)

                fragment.activity?.runOnUiThread {
                    idTextView?.text = fragment.getString(R.string.id, id)
                    emailTextView?.text = fragment.getString(R.string.id, email)
                    nicknameTextView?.text = fragment.getString(R.string.nickname, nickname)
                }
            }
        }
    })
}

