// ProfileFragment.kt
package com.example.ecoariumapp

import MypageFragment
import SharedPrefManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.sendRequests.*
import com.google.android.material.button.MaterialButton
import java.io.InputStream

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

        val imageView: ImageView = view.findViewById(R.id.profileImageView) //
        val profileImageEditButton: MaterialButton = view.findViewById(R.id.profileImageEditButton)
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
        val sharedPrefManager = SharedPrefManager(requireContext())
        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, it)
                val sharedPrefManager = SharedPrefManager(requireContext())
                // Get the current image Uri
                val currentImageUri = Uri.parse(sharedPrefManager.getSharedPrefereces().getString("profileImage", ""))
                Log.d("ProfileFragment", "currentImageUri: $currentImageUri")
                // Delete the current image from internal storage
                sharedPrefManager.deleteImageFromInternalStorage(currentImageUri)
                // Save the new image to internal storage
                val savedImageUri = sharedPrefManager.saveImageToInternalStorage(bitmap, requireContext())
                Log.d("ProfileFragment", "savedImageUri: $savedImageUri")
                // Set the new image
                sharedPrefManager.setImageView(savedImageUri)

                // MypageFragment로 이동
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.fragmentContainer, MypageFragment.newInstance())
                transaction?.commit()
            }
        }

        // SharedPreferences에서 "profileImage" 키에 해당하는 값을 가져옵니다.
        val imageUriString = sharedPrefManager.getSharedPrefereces().getString("profileImage", "")
        if (imageUriString!!.startsWith("/")) {
            // If the imageUriString is a file path
            val bitmap = BitmapFactory.decodeFile(imageUriString)
            imageView.setImageBitmap(bitmap)
        } else if (imageUriString.startsWith("android.resource://")) {
            // If the imageUriString is a resource identifier
            val imageUri = Uri.parse(imageUriString)
            val imageStream: InputStream? = context?.contentResolver?.openInputStream(imageUri)
            val bitmap = BitmapFactory.decodeStream(imageStream)
            imageView.setImageBitmap(bitmap)
        }

        profileImageEditButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("프로필 이미지 변경")
                .setItems(arrayOf("기본 이미지로 변경", "새로운 이미지로 변경")) { dialog, which ->
                    when (which) {
                        0 -> {
                            sharedPrefManager.setImageViewDefault()
                            // MypageFragment로 이동
                            val transaction = activity?.supportFragmentManager?.beginTransaction()
                            transaction?.replace(R.id.fragmentContainer, MypageFragment.newInstance())
                            transaction?.commit()
                        }
                        1 -> getContent.launch("image/*")
                    }
                }
                .show()
        }

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