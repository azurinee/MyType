package com.example.mytype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.mytype.fragment.ChatFragment
import com.example.mytype.fragment.HomeFragment
import com.example.mytype.fragment.MyPageFragment
import com.example.mytype.setType.SettingTypeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var num: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        initFragment()
        replaceFragment(HomeFragment())
    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else if (num ==1) {
            startActivity(Intent(this, SettingTypeActivity::class.java))
            num++
        }
    }

    private fun initFragment() {
        val navigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.chatting -> replaceFragment(ChatFragment())
                R.id.myPage -> replaceFragment(MyPageFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }
    }

}