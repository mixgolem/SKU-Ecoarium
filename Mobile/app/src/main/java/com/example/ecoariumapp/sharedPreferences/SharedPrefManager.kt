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
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)

    init {
        // Check if "profileImage" is not set or empty in SharedPreferences
        val profileImage = sharedPreferences.getString("profileImage", null)
        if (profileImage == null || profileImage.isEmpty()) {
            // Set the default image Uri
            setImageViewDefault()
        }
    }

    fun saveImageToInternalStorage(bitmap: Bitmap, context: Context): Uri {
        // Get the context wrapper
        val wrapper = ContextWrapper(context)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Return the saved image uri
        return Uri.parse(file.absolutePath)
    }

    fun getSharedPrefereces(): SharedPreferences {
        return sharedPreferences
    }

    fun deleteImageFromInternalStorage(imageUri: Uri) {
        val file = File(imageUri.path)
        if (file.exists()) {
            file.delete()
        }
    }


    fun setImageView(imageUri: Uri) {
        val editor = sharedPreferences.edit()
        editor.putString("profileImage", imageUri.toString())
        editor.apply()
    }

    fun setImageViewDefault() {
        val defaultImageUri = Uri.parse("android.resource://com.example.ecoariumapp/" + R.drawable.green)
        val editor = sharedPreferences.edit()
        editor.putString("profileImage", defaultImageUri.toString())
        editor.apply()
    }

    companion object {
        const val REQUEST_CODE_SELECT_IMAGE = 1
    }


    fun isCheckAutoLogin(): Boolean {
        return sharedPreferences.getBoolean("isLogin", false)
    }

    fun saveLoginDetails(id: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLogin", true)
        editor.putString("Id", id)
        editor.putString("Password", password)
        editor.apply()
    }

    fun userLogout(): Boolean {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLogin", false)
        editor.apply()
        val id = sharedPreferences.getString("Id", "")
        val password = sharedPreferences.getString("Password", "")
        return id != null && password != null
    }

    fun getSavedId(): String? {
        return sharedPreferences.getString("Id", null)
    }

    fun getSavedPassword(): String? {
        return sharedPreferences.getString("Password", null)
    }
}