package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.login.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.buttonSignUp.setOnClickListener {

            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            if (checkAllFields()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        auth.signOut()
                        Toast.makeText(this,"Account created successfully!",Toast.LENGTH_SHORT).show()
                        finish()
                        startActivity(Intent(this, SignInActivity::class.java))
                    }
                    else{
                        Log.e("error: ", it.exception.toString())
                    }
                }
            }
        }

    }

    private fun checkAllFields(): Boolean {

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
            binding.textInputLayoutPasssword.error = "This is required field"
            binding.textInputLayoutPasssword.errorIconDrawable = null
            return false
        }

        if (binding.editTextPassword.length() <= 7){
            binding.textInputLayoutPasssword.error = "Password should be at least 8 characters long "
            binding.textInputLayoutPasssword.errorIconDrawable = null
            return false
        }

        if (binding.editTextConfirmPassword.text.toString() == "") {
            binding.textInputLayoutConfirmPasssword.error = "This is required field"
            binding.textInputLayoutConfirmPasssword.errorIconDrawable = null
            return false
        }

        if (binding.editTextPassword.text.toString() != binding.editTextConfirmPassword.text.toString()) {
            binding.textInputLayoutPasssword.error = "Password do not match"
            return false
        }


        return true
    }
}