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

public fun sendModifyRequest(fragment: Fragment,nickname: String) {
    val json = JSONObject()
    json.put("nickname", nickname)

    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val body = json.toString().toRequestBody(mediaType)


    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/auth/modify")
        .put(body)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            println("원본 = " + response.body)
            if (response.isSuccessful) {

                fragment.activity?.runOnUiThread {
                    val transaction = fragment.parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainer, ProfileFragment())
                    transaction.commit()
                }
            }
        }
    })
}