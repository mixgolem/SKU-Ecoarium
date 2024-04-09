package com.example.ecoariumapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // UI 요소 참조
        val registerButton: Button = findViewById(R.id.registerButton)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val passwordVerificationEditText: EditText = findViewById(R.id.passwordVerificationEditText)
        val nicknameEditText: EditText = findViewById(R.id.nicknameEditText)

        // 회원가입 버튼 클릭 이벤트 설정
        registerButton.setOnClickListener {
            // 사용자 이름과 비밀번호 가져오기
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val passwordVerification = passwordVerificationEditText.text.toString()
            val nickname = nicknameEditText.text.toString()

            sendRegisterRequest(this, username, password, passwordVerification, nickname)
        }
    }
}