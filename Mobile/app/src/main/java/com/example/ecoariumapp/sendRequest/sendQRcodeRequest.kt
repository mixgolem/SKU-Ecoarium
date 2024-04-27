package com.example.ecoariumapp.sendRequest

import android.app.Activity
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

public fun sendQRcodeRequest(activity: Activity, imageView: ImageView) {
    val request = Request.Builder()
        .url("http://192.168.94.42:8000/main/createQR")
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
                println(responseBody)

                if (responseBody != null) {
                    // QR 코드 생성
                    val barcodeEncoder = BarcodeEncoder()
                    val bitmap: Bitmap = barcodeEncoder.encodeBitmap(responseBody, BarcodeFormat.QR_CODE, 400, 400)

                    // UI 업데이트는 메인 스레드에서 수행해야 하므로 Handler를 사용
                    Handler(Looper.getMainLooper()).post {
                        // QR 코드 표시
                        imageView.setImageBitmap(bitmap)
                    }
                }
            }
        }
    })
}