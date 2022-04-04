package com.example.mytype.setType

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mytype.R
import com.example.mytype.databinding.ActivitySettingTypeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SettingTypeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var userDB: DatabaseReference
    private lateinit var binding: ActivitySettingTypeBinding
    private var fragmentNum: Int = 1

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        binding = ActivitySettingTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        userDB = Firebase.database.reference.root

        replaceFragment(SetProfileFragment())
        initFragmentButton()
    }

    private fun initFragmentButton() {
        binding.settingTypeBottomBar.setOnClickListener {
            if (fragmentNum == 1) {
                val fragment = SetTypeFragment()
                replaceFragment(fragment)
                binding.typeFragmentKey.text = getString(R.string.complete_button)
                fragmentNum++
            } else {
                finish()
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.settingTypeContainer, fragment)
            commit()
        }
    }

}