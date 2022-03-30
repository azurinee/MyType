package com.example.mytype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytype.databinding.ActivitySettingUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SettingUserPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var userDB: DatabaseReference
    private lateinit var binding: ActivitySettingUserBinding

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        binding = ActivitySettingUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        userDB = Firebase.database.reference.root
    }
}