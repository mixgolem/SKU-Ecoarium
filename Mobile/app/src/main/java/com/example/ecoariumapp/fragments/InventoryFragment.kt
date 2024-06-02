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

class InventoryFragment: Fragment() {

    companion object {
        fun newInstance():InventoryFragment {
            return InventoryFragment()
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
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)

        val buttonAvailable: Button = view.findViewById(R.id.buttonAvailable)
        val buttonCompleted: Button = view.findViewById(R.id.buttonCompleted)

        buttonAvailable.isSelected = true
        sendAvailableItemRequest(this)

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

