package com.example.mytype.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mytype.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var auth: FirebaseAuth
    private lateinit var userDB: DatabaseReference

    private lateinit var nickName: TextView
    private lateinit var userSex: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        userDB = Firebase.database.reference.child("Users")

        nickName = view.findViewById<TextView>(R.id.nickNameTextView)
        userSex = view.findViewById<TextView>(R.id.sexTextView)
        setUserProfile()
    }

    private fun setUserProfile() {
        val currentUserDB = userDB.child(getCurrentUser())
        nickName.text = currentUserDB.child("nickName").toString()
        userSex.text = currentUserDB.child("sex").toString()
    }

    private fun getCurrentUser() : String {
        var userId: String? = null
        if (auth.currentUser == null) {
            Toast.makeText(requireContext(), getString(R.string.toast_login_need), Toast.LENGTH_SHORT).show()
        }
        return auth.currentUser?.uid.orEmpty()
    }

}