package com.example.ecoariumapp.activities

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.ecoariumapp.R
import com.example.ecoariumapp.sendRequests.sendRegisterRequest

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

        showPasswordVerificationButton.setOnClickListener {
            if (passwordVerificationEditText.transformationMethod is PasswordTransformationMethod) {
                // 비밀번호가 숨겨져 있을 때는 보이게 합니다.
                passwordVerificationEditText.transformationMethod = null
                showPasswordVerificationButton.isSelected = true // 상태를 변경합니다.
            } else {
                // 비밀번호가 보이는 상태일 때는 숨깁니다.
                passwordVerificationEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                showPasswordVerificationButton.isSelected = false // 상태를 변경합니다.
            }
        }

        showPasswordButton.setOnClickListener {
            if (passwordEditText.transformationMethod is PasswordTransformationMethod) {
                // 비밀번호가 숨겨져 있을 때는 보이게 합니다.
                passwordEditText.transformationMethod = null
                showPasswordButton.isSelected = true // 상태를 변경합니다.
            } else {
                // 비밀번호가 보이는 상태일 때는 숨깁니다.
                passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                showPasswordButton.isSelected = false // 상태를 변경합니다.
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

            sendRegisterRequest(this, username, password, passwordVerification, nickname, email)
        }
    }
}