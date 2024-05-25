
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ecoariumapp.R
import com.example.ecoariumapp.sendRequests.sendQRcodeRequest

class QRcodeFragment: Fragment() {

    companion object {
        fun newInstance():QRcodeFragment {
            return QRcodeFragment()
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

    private var timer: CountDownTimer? = null
    // 뷰가 생성되었을 때, 프래그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃과 조각을 서로 연결
        val view = inflater.inflate(R.layout.fragment_qrcode, container, false)
        val qrCodeImageView: ImageView = view.findViewById(R.id.qrCodeImageView)
        val timerTextView: TextView = view.findViewById(R.id.timerTextView)
        val qrNumberTextView: TextView = view.findViewById(R.id.qrNumberTextView)

        timer = object: CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                timerTextView.text = getString(R.string.qr_timer, seconds%60)
            }

            override fun onFinish() {
                timerTextView.text = "인증이 만료되었습니다."
            }
        }

        (timer as CountDownTimer).start()
        sendQRcodeRequest(this,qrCodeImageView,qrNumberTextView)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
    }

}