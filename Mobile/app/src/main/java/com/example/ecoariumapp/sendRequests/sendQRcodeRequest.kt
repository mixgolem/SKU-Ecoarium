package com.example.ecoariumapp.sendRequests

import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.IpConfig
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

fun sendQRcodeRequest(fragment: Fragment, imageView: ImageView) {
    val request = Request.Builder()
        .url("http://${IpConfig.serverIp}:8000/main/createQR")
        .get()
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 네트워크 오류 처리
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                Log.d("QRcodeFragment", "response: $responseBody")

                if (responseBody != null) {
                    // QR 코드 생성
                    val barcodeEncoder = BarcodeEncoder()
                    val bitmap: Bitmap = barcodeEncoder.encodeBitmap(responseBody, BarcodeFormat.QR_CODE, 400, 400)

                    // UI 업데이트는 메인 스레드에서 수행해야 하므로 runOnUiThread를 사용
                    fragment.activity?.runOnUiThread {
                        // QR 코드 표시
                        imageView.setImageBitmap(bitmap)
                    }
                }
            }
        }
    })
}