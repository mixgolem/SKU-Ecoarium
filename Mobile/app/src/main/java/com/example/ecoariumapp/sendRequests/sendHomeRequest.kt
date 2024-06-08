package com.example.ecoariumapp.sendRequests

import SharedPrefManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

// 프로필 정보를 요청하는 함수
fun sendHomeRequest(fragment: Fragment) {
    // GET 요청을 생성
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/mypage/load-profile")
        .get()
        .build()

    // 요청을 보내고 응답을 처리
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
                val sharedPrefManager = SharedPrefManager(fragment.requireContext())

                val jsonObject = JSONObject(jsonData)
                val userObject = jsonObject.getJSONObject("user")   //user
                val nickname = userObject.getString("nickname")     //nickname
                val points = jsonObject.getInt("points")            //point
                val imageUriString = sharedPrefManager.getSharedPrefereces()
                    .getString("profileImage", "")

                val pointTextView = fragment.view?.findViewById<TextView>(R.id.pointIntTextView)
                val nicknameTextView = fragment.view?.findViewById<TextView>(R.id.nicknameTextView)
                val imageView: ImageView = fragment.requireView().findViewById(R.id.homeImageView)

                // 이미지 URI에 따라 이미지를 로드
                if (imageUriString!!.startsWith("/")) {
                    // 파일 경로인 경우
                    val bitmap = BitmapFactory.decodeFile(imageUriString)
                    fragment.activity?.runOnUiThread {
                        imageView.setImageBitmap(bitmap)
                    }
                } else if (imageUriString.startsWith("android.resource://")) {
                    // 리소스 식별자인 경우
                    val imageUri = Uri.parse(imageUriString)
                    val imageStream: InputStream? = fragment.requireContext().contentResolver?.openInputStream(imageUri)
                    val bitmap = BitmapFactory.decodeStream(imageStream)
                    fragment.activity?.runOnUiThread {
                        imageView.setImageBitmap(bitmap)
                    }
                }

                // UI 업데이트
                fragment.activity?.runOnUiThread {
                    pointTextView?.text = points.toString()
                    nicknameTextView?.text = fragment.getString(R.string.nickname, nickname)
                }
            }
        }
    })
}