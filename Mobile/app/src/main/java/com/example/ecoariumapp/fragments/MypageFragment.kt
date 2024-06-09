import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoariumapp.ProfileFragment
import com.example.ecoariumapp.R
import com.example.ecoariumapp.sendRequests.*
import java.io.InputStream

// 사용자의 마이페이지를 표시하는 프래그먼트
class MypageFragment: Fragment() {

    companion object {
        // MypageFragment 인스턴스 생성
        fun newInstance():MypageFragment {
            return MypageFragment()
        }
    }

    // 프래그먼트 생성 시 호출
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // 프래그먼트가 액티비티에 연결될 때 호출
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    // 프래그먼트의 뷰 생성 시 호출
    // 레이아웃 인플레이트, 로그와 마이페이지 요청
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃 인플레이트
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)
        // 로그와 마이페이지 요청
        sendAllLogsRequest(this)
        sendMypageRequest(this)

        // 뷰 요소 참조
        val recyclerView = view.findViewById<RecyclerView>(R.id.stampRecyclerView)
        recyclerView?.layoutManager=LinearLayoutManager(context)

        val editProfileButton = view.findViewById<Button>(R.id.editProfileButton)
        val logoutButton = view.findViewById<Button>(R.id.logoutButton)

        val allButton: Button = view.findViewById(R.id.allButton)
        val accumulationButton: Button = view.findViewById(R.id.accumulationButton)
        val usageButton: Button = view.findViewById(R.id.usageButton)

        allButton.isSelected = true

        // 프로필 편집 버튼 클릭 이벤트 핸들러
        editProfileButton.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainer, ProfileFragment.newInstance())
            transaction?.commit()
        }

        // 버튼 클릭 이벤트 핸들러
        allButton.setOnClickListener {
            sendAllLogsRequest(this)
            allButton.isSelected = true
            accumulationButton.isSelected = false
            usageButton.isSelected = false
        }

        accumulationButton.setOnClickListener {
            sendStampLogsRequest(this)
            allButton.isSelected = false
            accumulationButton.isSelected = true
            usageButton.isSelected = false
        }

        usageButton.setOnClickListener {
            sendItemsLogsRequest(this)
            allButton.isSelected = false
            accumulationButton.isSelected = false
            usageButton.isSelected = true
        }

        // 로그아웃 버튼 클릭 이벤트 핸들러
        logoutButton.setOnClickListener {
            sendLogoutRequest(this)
        }

        return view
    }

    // 프래그먼트의 뷰가 생성된 후 호출
    // 마이페이지 요청, 프로필 이미지 설정
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 마이페이지 요청
        sendMypageRequest(this)

        // SharedPrefManager 인스턴스 생성
        val sharedPrefManager = SharedPrefManager(requireContext())
        // SharedPreferences에서 "profileImage" 키에 해당하는 값 가져오기
        val imageUriString = sharedPrefManager.getSharedPrefereces().getString("profileImage", "")

        val imageView: ImageView = view.findViewById(R.id.mypageImageView)

        if (imageUriString!!.startsWith("/")) {
            // 이미지UriString이 파일 경로인 경우
            val bitmap = BitmapFactory.decodeFile(imageUriString)
            imageView.setImageBitmap(bitmap)
        } else if (imageUriString.startsWith("android.resource://")) {
            // 이미지UriString이 리소스 식별자인 경우
            val imageUri = Uri.parse(imageUriString)
            val imageStream: InputStream? = context?.contentResolver?.openInputStream(imageUri)
            val bitmap = BitmapFactory.decodeStream(imageStream)
            imageView.setImageBitmap(bitmap)
        }
    }
}