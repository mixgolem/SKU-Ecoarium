import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.R
import com.example.ecoariumapp.sendRequests.sendLoadItemsRequest

// 상점 화면을 표시하는 프래그먼트
class StoreFragment: Fragment() {

    companion object {
        // StoreFragment 인스턴스를 생성하는 팩토리 메서드
        fun newInstance():StoreFragment {
            return StoreFragment()
        }
    }

    // 프래그먼트가 메모리에 생성될 때 호출되는 메서드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // 프래그먼트가 액티비티에 연결될 때 호출되는 메서드
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    // 프래그먼트의 뷰가 생성될 때 호출되는 메서드
    // 프래그먼트의 레이아웃을 인플레이트하고, 아이템 로드 요청을 보냄
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃 인플레이션
        val view = inflater.inflate(R.layout.fragment_store, container, false)
        // 아이템 로드 요청
        sendLoadItemsRequest(this)

        return view
    }

}