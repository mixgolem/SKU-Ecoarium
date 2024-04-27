// BottomNavigationManager.kt
package com.example.ecoariumapp

import android.content.Intent
import com.example.ecoariumapp.databinding.ViewBottomNavBinding

fun setBottomNavigationView(binding: ViewBottomNavBinding) {
    binding.bottomNavigationView.setOnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.fragmentHome -> {
                val intent = Intent(binding.root.context, HomeActivity::class.java)
                binding.root.context.startActivity(intent)
                true
            }
            R.id.fragmentStore -> {
                val intent = Intent(binding.root.context, StoreActivity::class.java)
                binding.root.context.startActivity(intent)
                true
            }
            R.id.fragmentQRcode -> {
                val intent = Intent(binding.root.context, QRcodeActivity::class.java)
                binding.root.context.startActivity(intent)
                true
            }
            R.id.fragmentInventory -> {
                val intent = Intent(binding.root.context, InventoryActivity::class.java)
                binding.root.context.startActivity(intent)
                true
            }
            R.id.fragmentMypage -> {
                val intent = Intent(binding.root.context, MypageActivity::class.java)
                binding.root.context.startActivity(intent)
                true
            }
            else -> false
        }
    }
}