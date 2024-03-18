package com.example.ecoariumapp

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecoariumapp.databinding.ActivityHomeBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그아웃 버튼 클릭 시 MainActivity로 이동
        binding.logoutButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish() // HomeActivity 종료
        }

        // QR 코드 생성 및 보여주기
        generateAndDisplayQRCode("Your QR Code Data")
    }

    private fun generateAndDisplayQRCode(data: String) {
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap: Bitmap = barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, 400, 400)
            binding.qrCodeImageView.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
