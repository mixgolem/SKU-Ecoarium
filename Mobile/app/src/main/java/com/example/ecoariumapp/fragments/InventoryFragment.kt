import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.R
import com.example.ecoariumapp.sendRequests.sendAvailableItemRequest
import com.example.ecoariumapp.sendRequests.sendCompletedItemRequest

// 인벤토리를 표시하는 프래그먼트
class InventoryFragment: Fragment() {

    companion object {
        // InventoryFragment 인스턴스를 생성하는 팩토리 메서드
        fun newInstance():InventoryFragment {
            return InventoryFragment()
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
    // 프래그먼트의 레이아웃을 인플레이트하고, 사용 가능한 아이템과 완료된 아이템 요청을 보냄
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃 인플레이션
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)

        // 뷰 요소 참조 가져오기
        val buttonAvailable: Button = view.findViewById(R.id.buttonAvailable)
        val buttonCompleted: Button = view.findViewById(R.id.buttonCompleted)

        // 사용 가능한 아이템 요청 보내기
        buttonAvailable.isSelected = true
        sendAvailableItemRequest(this)

        // 버튼 클릭 이벤트 핸들러 설정
        buttonAvailable.setOnClickListener {
            buttonAvailable.isSelected = true
            buttonCompleted.isSelected = false
            sendAvailableItemRequest(this)
        }

        buttonCompleted.setOnClickListener {
            buttonAvailable.isSelected = false
            buttonCompleted.isSelected = true
            sendCompletedItemRequest(this)
        }

        return view
    }
}