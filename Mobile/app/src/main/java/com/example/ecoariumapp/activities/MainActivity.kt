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

class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var homeFragment: HomeFragment
    private lateinit var inventoryFragment: InventoryFragment
    private lateinit var mypageFragment: MypageFragment
    private lateinit var qrcodeFragment: QRcodeFragment
    private lateinit var storeFragment: StoreFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_bottom_nav)

        val sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) {
            // 로그인 상태가 아니므로 LoginActivity로 이동
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNav.setOnNavigationItemSelectedListener(this)

        homeFragment = HomeFragment.newInstance()
        // 처음에는 add로 추가해서 셋팅해주고
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, homeFragment).commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // 클릭하면 계속 교체 replace
        when(item.itemId) {
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

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if (currentFragment is ProfileFragment) {
            // ProfileFragment에서 뒤로가기 버튼이 눌렸을 때 MypageFragment로 이동
            val mypageFragment = MypageFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, mypageFragment).commit()
        }
    }
}