package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.login.R
import com.example.login.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth


        binding.buttonForgotPassword.setOnClickListener {

            if (checkEmail()) {
                val email = binding.editTextEmail.text.toString()
                auth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            this,
                            "A password reset message will be sent to your recovery email address.",
                            Toast.LENGTH_LONG
                        ).show()

                        val intent = Intent(this, SignInActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Log.e("Error: ", it.exception.toString())

                    }
                }
            }


        }


    }

    private fun checkEmail(): Boolean {


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