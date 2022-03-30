package com.example.mytype

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytype.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var fireDB: DatabaseReference
    private lateinit var binding: ActivityLoginBinding;

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        fireDB = Firebase.database.reference.root

        initSignInButton()
        initSignUpButton()
    }

    private fun initSignInButton() {
        binding.signInButton.setOnClickListener {
            val email = binding.emailEditText
            val password = binding.passwordEditText

            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful)
                        finish()
                    else
                        Toast.makeText(this, getString(R.string.login_fail_message), Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun initSignUpButton() {
        binding.signUpPageButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

}