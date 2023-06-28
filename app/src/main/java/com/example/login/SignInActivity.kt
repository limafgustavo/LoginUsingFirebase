package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.login.databinding.ActivitySignInBinding
import com.example.login.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)

        binding.buttonSignIn.setOnClickListener {

            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (checkAllField()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this,"Successfully sign in.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,HomeActivity::class.java))
                        finish()
                    }else{
                        Log.e("error: ", it.exception.toString())
                    }
                }
            }
        }
    }

    private fun checkAllField(): Boolean {

        val email = binding.editTextEmail.text.toString()

        if (binding.editTextEmail.text.toString() == "") {
            binding.textInputLayoutEmail.error = "This is required field"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputLayoutEmail.error = "Check email format"
            return false
        }
        if (binding.editTextPassword.text.toString() == "") {
            binding.textInputLayoutPassword.error = "This is required field"
            binding.textInputLayoutPassword.errorIconDrawable = null
            return false
        }

        if (binding.editTextPassword.length() <= 7) {
            binding.textInputLayoutPassword.error = "Password should be at least 8 characters long "
            binding.textInputLayoutPassword.errorIconDrawable = null
            return false
        }
        return true
    }
}