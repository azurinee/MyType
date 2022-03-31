package com.example.mytype.setType

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytype.R
import com.example.mytype.databinding.FragmentSetProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SetProfileFragment : Fragment() {
    private var _binding: FragmentSetProfileBinding? = null
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth
    private lateinit var userDB: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetProfileBinding.inflate(inflater, container, false)
        val view = binding?.root

        auth = Firebase.auth
        userDB = Firebase.database.reference.child("Users")

        return view
    }

    override fun onPause() {
        super.onPause()

        if (auth.currentUser != null) {
            saveUserProfile()
        }
    }

    private fun saveUserProfile() {
        var userId = auth.currentUser?.uid.orEmpty()
        var profile = mutableMapOf<String, Any>()
        val currentUserDB = userDB.child("userId")
        profile["userId"] = userId
        profile["nickName"] = binding?.nickNameEditText?.text.toString()
        currentUserDB.updateChildren(profile)
    }
}