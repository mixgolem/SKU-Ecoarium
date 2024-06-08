import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import com.example.ecoariumapp.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class SharedPrefManager(context: Context) {
    // SharedPreferences 인스턴스 생성
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)

    init {
        // "profileImage"가 SharedPreferences에 설정되지 않았거나 비어 있는지 확인
        val profileImage = sharedPreferences.getString("profileImage", null)
        if (profileImage == null || profileImage.isEmpty()) {
            // 기본 이미지 Uri 설정
            setImageViewDefault()
        }
    }

    // 내부 저장소에 이미지 저장
    fun saveImageToInternalStorage(bitmap: Bitmap, context: Context): Uri {
        // 컨텍스트 래퍼 가져오기
        val wrapper = ContextWrapper(context)

        // 비트맵 객체를 저장할 새 파일 인스턴스 초기화
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            // 비트맵을 압축하고 jpg 형식으로 저장
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // 저장된 이미지 uri 반환
        return Uri.parse(file.absolutePath)
    }

    // SharedPreferences 가져오기
    fun getSharedPrefereces(): SharedPreferences {
        return sharedPreferences
    }

    // 내부 저장소에서 이미지 삭제
    fun deleteImageFromInternalStorage(imageUri: Uri) {
        val file = File(imageUri.path)
        if (file.exists()) {
            file.delete()
        }
    }

    // 이미지 뷰 설정
    fun setImageView(imageUri: Uri) {
        val editor = sharedPreferences.edit()
        editor.putString("profileImage", imageUri.toString())
        editor.apply()
    }

    // 기본 이미지 뷰 설정
    fun setImageViewDefault() {
        val defaultImageUri = Uri.parse("android.resource://com.example.ecoariumapp/" + R.drawable.green)
        val editor = sharedPreferences.edit()
        editor.putString("profileImage", defaultImageUri.toString())
        editor.apply()
    }

    companion object {
        const val REQUEST_CODE_SELECT_IMAGE = 1
    }

    // 자동 로그인 체크 확인
    fun isCheckAutoLogin(): Boolean {
        return sharedPreferences.getBoolean("isLogin", false)
    }

    // 로그인 정보 저장
    fun saveLoginDetails(id: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLogin", true)
        editor.putString("Id", id)
        editor.putString("Password", password)
        editor.apply()
    }

    // 사용자 로그아웃
    fun userLogout(): Boolean {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLogin", false)
        editor.apply()
        val id = sharedPreferences.getString("Id", "")
        val password = sharedPreferences.getString("Password", "")
        return id != null && password != null
    }

    // 저장된 ID 가져오기
    fun getSavedId(): String? {
        return sharedPreferences.getString("Id", null)
    }

    // 저장된 비밀번호 가져오기
    fun getSavedPassword(): String? {
        return sharedPreferences.getString("Password", null)
    }
}