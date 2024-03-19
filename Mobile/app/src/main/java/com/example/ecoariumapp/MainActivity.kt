package com.example.ecoariumapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton: Button = findViewById(R.id.loginButton)
        val registerButton: Button = findViewById(R.id.registerButton) // 회원가입 버튼 추가
        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // 사용자 이름과 비밀번호를 검증
            if (validateLogin(username, password)) {
                // 검증이 성공하면 HomeActivity로 이동
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish() // 현재 액티비티 종료
            } else {
                // 검증이 실패하면 오류 메시지 표시
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            // 회원가입 버튼 클릭 시 RegisterActivity로 이동
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateLogin(username: String, password: String): Boolean {

        val hardcodedUsername = "user123"
        val hardcodedPassword = "password123"

        return username == hardcodedUsername && password == hardcodedPassword
    }
}
