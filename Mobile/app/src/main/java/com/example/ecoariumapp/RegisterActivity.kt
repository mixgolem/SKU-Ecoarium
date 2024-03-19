package com.example.ecoariumapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerButton: Button = findViewById(R.id.registerButton)
        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // 사용자 이름과 비밀번호를 검증
            if (validateRegister(username, password)) {
                // 회원가입 성공 메시지 표시
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                // 로그인 화면으로 이동
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // 현재 액티비티 종료
            } else {
                // 검증이 실패하면 오류 메시지 표시
                Toast.makeText(this, "Failed to register. Username or password is invalid.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateRegister(username: String, password: String): Boolean {
        // 간단한 예시로서, 이메일 형식의 유효성만 검사하도록 함
        // 실제로는 더 강력한 검증이 필요할 수 있음
        return android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches() && password.isNotEmpty()
    }
}
