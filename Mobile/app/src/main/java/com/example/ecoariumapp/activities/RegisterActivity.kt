package com.example.ecoariumapp.activities

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.ecoariumapp.R
import com.example.ecoariumapp.sendRequests.sendRegisterRequest

// 회원가입 화면을 관리하는 액티비티
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
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val showPasswordButton: ImageButton = findViewById(R.id.showPasswordButton)
        val showPasswordVerificationButton: ImageButton = findViewById(R.id.showPasswordVerificationButton)

        // 비밀번호 확인 표시/숨김 버튼 클릭 이벤트 설정
        showPasswordVerificationButton.setOnClickListener {
            if (passwordVerificationEditText.transformationMethod is PasswordTransformationMethod) {
                passwordVerificationEditText.transformationMethod = null
                showPasswordVerificationButton.isSelected = true // 버튼 상태를 변경합니다.
            } else {
                passwordVerificationEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                showPasswordVerificationButton.isSelected = false // 버튼 상태를 변경합니다.
            }
        }

        // 비밀번호 표시/숨김 버튼 클릭 이벤트 설정
        showPasswordButton.setOnClickListener {
            if (passwordEditText.transformationMethod is PasswordTransformationMethod) {
                passwordEditText.transformationMethod = null
                showPasswordButton.isSelected = true // 버튼 상태를 변경합니다.
            } else {
                passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                showPasswordButton.isSelected = false // 버튼 상태를 변경합니다.
            }
        }

        // 회원가입 버튼 클릭 이벤트 설정
        registerButton.setOnClickListener {
            // 사용자 이름과 비밀번호 가져오기
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val passwordVerification = passwordVerificationEditText.text.toString()
            val nickname = nicknameEditText.text.toString()
            val email = emailEditText.text.toString()

            // 회원가입 요청 보내기
            sendRegisterRequest(this, username, password, passwordVerification, nickname, email)
        }
    }
}