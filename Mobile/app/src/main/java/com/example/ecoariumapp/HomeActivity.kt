// HomeActivity.kt
package com.example.ecoariumapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ecoariumapp.databinding.ViewBottomNavBinding
import com.example.ecoariumapp.sendRequest.sendLogoutRequest

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val binding = ViewBottomNavBinding.inflate(layoutInflater)
        setBottomNavigationView(binding)

        val logoutButton: Button = findViewById(R.id.logoutButton)
        val qrcodeButton: Button = findViewById(R.id.QRcodeButton)
        // 로그아웃 버튼 클릭 이벤트 설정
        logoutButton.setOnClickListener {
            sendLogoutRequest(this)
        }
        qrcodeButton.setOnClickListener{
            val intent = Intent(this, QRcodeActivity::class.java)
            startActivity(intent)
        }
    }
}