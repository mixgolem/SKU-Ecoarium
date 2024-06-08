import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.ecoariumapp.R
import com.example.ecoariumapp.adapters.ViewPagerAdapter
import com.example.ecoariumapp.sendRequests.sendHomeRequest
import com.example.ecoariumapp.sendRequests.sendStacksRequest
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// 홈 화면을 표시하는 프래그먼트
class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager2

    companion object {
        // HomeFragment 인스턴스를 생성하는 팩토리 메서드
        fun newInstance(): HomeFragment {
            return HomeFragment()
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
    // 프래그먼트의 레이아웃을 인플레이트하고, 뷰페이저와 탭 레이아웃을 설정
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        viewPager = view.findViewById(R.id.viewPagerEvent)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        viewPager.adapter = ViewPagerAdapter(getEventList())

        // 탭 레이아웃과 뷰페이저를 연결
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            //Some implementation
        }.attach()

        return view
    }

    // 프래그먼트의 뷰가 생성된 후 호출되는 메서드
    // 홈 요청과 스택 요청을 보냄
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendHomeRequest(this)
        sendStacksRequest(this)
    }

    // 이벤트 리스트를 가져오는 메서드
    private fun getEventList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.event1,
            R.drawable.event2, R.drawable.event3, R.drawable.event4)
    }
}