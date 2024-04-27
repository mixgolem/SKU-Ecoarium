package com.example.ecoariumapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.ecoariumapp.sendRequest.sendQRcodeRequest

class InventoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)

        val qrCodeImageView: ImageView = findViewById(R.id.qrCodeImageView)

        sendQRcodeRequest(this, qrCodeImageView)
    }
}