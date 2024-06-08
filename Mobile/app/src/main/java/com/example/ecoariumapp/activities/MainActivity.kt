package com.example.ecoariumapp.activities

import HomeFragment
import InventoryFragment
import MypageFragment
import QRcodeFragment
import StoreFragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ecoariumapp.ProfileFragment
import com.example.ecoariumapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

// 메인 화면을 관리하는 액티비티
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var inventoryFragment: InventoryFragment
    private lateinit var mypageFragment: MypageFragment
    private lateinit var qrcodeFragment: QRcodeFragment
    private lateinit var storeFragment: StoreFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_bottom_nav)

        // 로그인 상태 확인
        val sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        // 로그인 상태가 아니면 LoginActivity로 이동
        if (!isLoggedIn) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 하단 네비게이션 바 설정
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNav.setOnNavigationItemSelectedListener(this)

        // 초기 화면 설정
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, homeFragment).commit()
    }

    // 하단 네비게이션 아이템 선택 이벤트 처리
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.fragmentHome -> {
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit()
            }
            R.id.fragmentInventory -> {
                inventoryFragment = InventoryFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, inventoryFragment).commit()
            }
            R.id.fragmentMypage -> {
                mypageFragment = MypageFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, mypageFragment).commit()
            }
            R.id.fragmentStore -> {
                storeFragment = StoreFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, storeFragment).commit()
            }
            R.id.fragmentQRcode -> {
                qrcodeFragment = QRcodeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, qrcodeFragment).commit()
            }
        }
        return true
    }

    // 뒤로가기 버튼 이벤트 처리
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        // ProfileFragment에서 뒤로가기 버튼이 눌렸을 때 MypageFragment로 이동
        if (currentFragment is ProfileFragment) {
            val mypageFragment = MypageFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, mypageFragment).commit()
        }
    }
}