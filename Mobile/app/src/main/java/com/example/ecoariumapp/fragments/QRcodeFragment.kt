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

// QR 코드를 표시하는 프래그먼트
class QRcodeFragment: Fragment() {

    companion object {
        // QRcodeFragment 인스턴스를 생성하는 팩토리 메서드
        fun newInstance():QRcodeFragment {
            return QRcodeFragment()
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

    private var timer: CountDownTimer? = null
    // 프래그먼트의 뷰가 생성될 때 호출되는 메서드
    // 프래그먼트의 레이아웃을 인플레이트하고, QR 코드 요청을 보냄
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃 인플레이션
        val view = inflater.inflate(R.layout.fragment_qrcode, container, false)
        val qrCodeImageView: ImageView = view.findViewById(R.id.qrCodeImageView)
        val timerTextView: TextView = view.findViewById(R.id.timerTextView)
        val qrNumberTextView: TextView = view.findViewById(R.id.qrNumberTextView)

        // 타이머 설정
        timer = object: CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                timerTextView.text = getString(R.string.qr_timer, seconds%60)
            }

            override fun onFinish() {
                timerTextView.text = "인증이 만료되었습니다."
            }
        }

        // 타이머 시작
        (timer as CountDownTimer).start()
        // QR 코드 요청
        sendQRcodeRequest(this,qrCodeImageView,qrNumberTextView)

        return view
    }

    // 프래그먼트의 뷰가 파괴될 때 호출되는 메서드
    // 타이머를 취소
    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
    }

}