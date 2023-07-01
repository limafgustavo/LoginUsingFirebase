package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.login.databinding.ActivityUpdateEmailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UpdateEmailActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityUpdateEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateEmailBinding.inflate(layoutInflater)
        auth = Firebase.auth

        setContentView(binding.root)
        binding.buttonSignOut.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)

            finish()
        }

        binding.buttonUpdateEmail.setOnClickListener {
            if (checkEmailField()) {
                val email = binding.editTextEmail.text.toString()

                auth.currentUser?.updateEmail(email)?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Updated sucessfully", Toast.LENGTH_SHORT).show()

                        intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.e("Error: ", it.exception.toString())
                    }
                }

            }

        }


    }

    private fun checkEmailField(): Boolean {
        val email = binding.editTextEmail.text.toString()

        if (binding.editTextEmail.text.toString() == "") {
            binding.textInputLayoutEmail.error = "This is required field"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputLayoutEmail.error = "Check email format"
            return false
        }

        return true
    }
}