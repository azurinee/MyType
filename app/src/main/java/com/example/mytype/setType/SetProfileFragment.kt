package com.example.mytype.setType

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.mytype.R
import com.example.mytype.databinding.FragmentSetProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SetProfileFragment : Fragment() {
    private lateinit var binding: FragmentSetProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var userDB: DatabaseReference

    private lateinit var userSex: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetProfileBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        userDB = Firebase.database.reference.child("Users")

        onRadioButtonChecked()

        return binding.root
    }

    override fun onPause() {
        super.onPause()

        if (auth.currentUser != null) {
            saveUserProfile()
        }
    }

    private fun saveUserProfile() {
        val userId = auth.currentUser?.uid.orEmpty()
        val profile = mutableMapOf<String, Any>()
        val currentUserDB = userDB.child(userId)
        profile["nickName"] = binding?.nickNameEditText?.text.toString()
        profile["sex"] = userSex
        currentUserDB.updateChildren(profile)
    }

    private fun onRadioButtonChecked() {
        val checkSex = binding.radioGroupSex
        checkSex.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.sex_female -> userSex = "여성"
                R.id.sex_male -> userSex = "남성"
            }
        }
    }

}