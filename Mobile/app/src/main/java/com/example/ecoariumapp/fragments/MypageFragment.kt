
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.ProfileFragment
import com.example.ecoariumapp.R
import com.example.ecoariumapp.sendRequests.sendLogoutRequest
import com.example.ecoariumapp.sendRequests.sendMypageRequest

class MypageFragment: Fragment() {

    companion object {
        fun newInstance():MypageFragment {
            return MypageFragment()
        }
    }

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // 프래그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    // 뷰가 생성되었을 때, 프래그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃과 조각을 서로 연결
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)
        sendMypageRequest(this)

        val editProfileButton = view.findViewById<Button>(R.id.editProfileButton)
        val logoutButton = view.findViewById<Button>(R.id.logoutButton)

        editProfileButton.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainer, ProfileFragment.newInstance())
            transaction?.commit()
        }

        logoutButton.setOnClickListener {
            sendLogoutRequest(this)
            // 로그아웃 버튼 클릭 시 수행할 작업
        }

        return view
    }

}