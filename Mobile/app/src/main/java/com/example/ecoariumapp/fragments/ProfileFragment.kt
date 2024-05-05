// ProfileFragment.kt
package com.example.ecoariumapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.sendRequests.sendDeleteRequest
import com.example.ecoariumapp.sendRequests.sendLogoutRequest
import com.example.ecoariumapp.sendRequests.sendModifyRequest

class ProfileFragment : Fragment() {
    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nicknameChangeButton: Button = view.findViewById(R.id.nicknameChangeButton)
        val nicknameConfirmButton: Button = view.findViewById(R.id.nicknameConfirmButton)
        val passwordChangeButton: Button = view.findViewById(R.id.passwordChangeButton)
        val logoutButton : Button = view.findViewById(R.id.logoutButton)
        val deleteAccountButton: Button = view.findViewById(R.id.deleteAccountButton)
        val nicknameTextView: TextView = view.findViewById(R.id.nicknameTextView)
        val nicknameEditText: EditText = view.findViewById(R.id.nicknameEditText)
        val rootRelativeLayout: RelativeLayout = view.findViewById(R.id.rootRelativeLayout)
        val nicknameDivider : View = view.findViewById(R.id.nicknameDivider)
        val deleteAccountConfirmButton : Button = view.findViewById(R.id.deleteAccountConfirmButton)
        val deleteAccountEditText : EditText = view.findViewById(R.id.deleteAccountEditText)


        nicknameChangeButton.setOnClickListener {
            // TextView를 숨기고 EditText를 보이게 함
            nicknameTextView.visibility = View.GONE
            nicknameEditText.visibility = View.VISIBLE
            nicknameChangeButton.visibility = View.GONE
            nicknameConfirmButton.visibility = View.VISIBLE
            nicknameDivider.visibility = View.GONE

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
            if (nicknameEditText.visibility == View.VISIBLE) {
                nicknameEditText.visibility = View.GONE
                nicknameTextView.visibility = View.VISIBLE
                nicknameChangeButton.visibility = View.VISIBLE
                nicknameConfirmButton.visibility = View.GONE
                nicknameDivider.visibility = View.VISIBLE
                deleteAccountEditText.visibility = View.GONE
                deleteAccountConfirmButton.visibility = View.GONE
                deleteAccountButton.visibility = View.VISIBLE
                nicknameEditText.setText("")
                deleteAccountEditText.setText("")
            }
        }

        passwordChangeButton.setOnClickListener {
            // 비밀번호 변경 로직
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
    }
}