// ProfileFragment.kt
package com.example.ecoariumapp

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.sendRequests.*

class ProfileFragment : Fragment() {
    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ProfileFragment", "onCreateView 시작")
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        Log.d("ProfileFragment", "onCreateView 완료")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ProfileFragment", "onViewCreated 시작")
        super.onViewCreated(view, savedInstanceState)

        sendProfileRequest(this)

        val nicknameChangeButton: Button = view.findViewById(R.id.nicknameChangeButton)
        val nicknameConfirmButton: Button = view.findViewById(R.id.nicknameConfirmButton)
        val logoutButton : Button = view.findViewById(R.id.logoutButton)
        val deleteAccountButton: Button = view.findViewById(R.id.deleteAccountButton)
        val nicknameTextView: TextView = view.findViewById(R.id.nicknameTextView)
        val nicknameEditText: EditText = view.findViewById(R.id.nicknameEditText)
        val rootRelativeLayout: RelativeLayout = view.findViewById(R.id.rootRelativeLayout)
        val deleteAccountConfirmButton : Button = view.findViewById(R.id.deleteAccountConfirmButton)
        val deleteAccountEditText : EditText = view.findViewById(R.id.deleteAccountEditText)


        val passwordChangeButton: Button = view.findViewById(R.id.passwordChangeButton)
        val passwordConfirmButton: Button = view.findViewById(R.id.passwordConfirmButton)
        val currentPasswordEditText: EditText = view.findViewById(R.id.currentPasswordEditText)
        val newPasswordEditText: EditText = view.findViewById(R.id.newPasswordEditText)
        val newPasswordVerificationEditText: EditText = view.findViewById(R.id.newPasswordVerificationEditText)
        val showCurrentPasswordButton: ImageButton = view.findViewById(R.id.showCurrentPasswordButton)
        val showNewPasswordButton: ImageButton = view.findViewById(R.id.showNewPasswordButton)
        val showNewPasswordVerificationButton: ImageButton = view.findViewById(R.id.showNewPasswordVerificationButton)

        showCurrentPasswordButton.setOnClickListener {
            togglePasswordVisibility(currentPasswordEditText, showCurrentPasswordButton)
        }

        showNewPasswordButton.setOnClickListener {
            togglePasswordVisibility(newPasswordEditText, showNewPasswordButton)
        }

        showNewPasswordVerificationButton.setOnClickListener {
            togglePasswordVisibility(newPasswordVerificationEditText, showNewPasswordVerificationButton)
        }
        nicknameChangeButton.setOnClickListener {
            // TextView를 숨기고 EditText를 보이게 함
            nicknameTextView.visibility = View.GONE
            nicknameEditText.visibility = View.VISIBLE
            nicknameChangeButton.visibility = View.GONE
            nicknameConfirmButton.visibility = View.VISIBLE

            nicknameEditText.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val newNickname = nicknameEditText.text.toString()
                    // newNickname을 서버에 전송하는 로직
                    nicknameEditText.visibility = View.GONE
                    nicknameTextView.visibility = View.VISIBLE
                    nicknameTextView.text = newNickname
                    true
                } else {
                    false
                }
            }
        }

        nicknameConfirmButton.setOnClickListener {
            val newNickname = nicknameEditText.text.toString()
            sendModifyRequest(this,newNickname)
        }

        // 루트 뷰에 클릭 리스너 설정
        rootRelativeLayout.setOnClickListener {
            // EditText가 보이는 상태라면, EditText를 숨기고 TextView를 다시 보이게 함
            if ((nicknameEditText.visibility == View.VISIBLE) || (passwordConfirmButton.visibility == View.VISIBLE)) {
                nicknameEditText.visibility = View.GONE
                nicknameTextView.visibility = View.VISIBLE
                nicknameChangeButton.visibility = View.VISIBLE
                nicknameConfirmButton.visibility = View.GONE
                deleteAccountEditText.visibility = View.GONE
                deleteAccountConfirmButton.visibility = View.GONE
                deleteAccountButton.visibility = View.VISIBLE
                passwordChangeButton.visibility = View.VISIBLE
                passwordConfirmButton.visibility = View.GONE
                currentPasswordEditText.visibility = View.GONE
                newPasswordEditText.visibility = View.GONE
                newPasswordVerificationEditText.visibility = View.GONE
                showCurrentPasswordButton.visibility = View.GONE
                showNewPasswordButton.visibility = View.GONE
                showNewPasswordVerificationButton.visibility = View.GONE
                showCurrentPasswordButton.isSelected = false
                showNewPasswordButton.isSelected = false
                showNewPasswordVerificationButton.isSelected = false
                nicknameEditText.setText("")
                deleteAccountEditText.setText("")
                currentPasswordEditText.setText("")
                newPasswordEditText.setText("")
                newPasswordVerificationEditText.setText("")
            }
        }

        passwordChangeButton.setOnClickListener {
            // TextView를 숨기고 EditText를 보이게 함
            passwordChangeButton.visibility = View.GONE
            passwordConfirmButton.visibility = View.VISIBLE
            currentPasswordEditText.visibility = View.VISIBLE
            newPasswordEditText.visibility = View.VISIBLE
            newPasswordVerificationEditText.visibility = View.VISIBLE
            showCurrentPasswordButton.visibility = View.VISIBLE
            showNewPasswordButton.visibility = View.VISIBLE
            showNewPasswordVerificationButton.visibility = View.VISIBLE
        }

        passwordConfirmButton.setOnClickListener {
            val currentPassword = currentPasswordEditText.text.toString()
            val newPassword = newPasswordEditText.text.toString()
            val newPasswordVerification = newPasswordVerificationEditText.text.toString()
            sendChangePasswordRequest(this,currentPassword,newPassword,newPasswordVerification)
        }

        logoutButton.setOnClickListener{
            sendLogoutRequest(this)
        }

        deleteAccountButton.setOnClickListener {
            // TextView를 숨기고 EditText를 보이게 함
            deleteAccountEditText.visibility = View.VISIBLE
            deleteAccountConfirmButton.visibility = View.VISIBLE
            deleteAccountButton.visibility = View.GONE
        }

        deleteAccountConfirmButton.setOnClickListener {
            val passwordConfirm = deleteAccountEditText.text.toString()
            sendDeleteRequest(this,passwordConfirm)
        }
        Log.d("ProfileFragment", "onViewCreated 완료")
    }

    private fun togglePasswordVisibility(editText: EditText, button: ImageButton) {
        if (editText.transformationMethod is PasswordTransformationMethod) {
            // 비밀번호가 숨겨져 있을 때는 보이게 합니다.
            editText.transformationMethod = null
            button.isSelected = true // 상태를 변경합니다.
        } else {
            // 비밀번호가 보이는 상태일 때는 숨깁니다.
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            button.isSelected = false // 상태를 변경합니다.
        }
    }
}