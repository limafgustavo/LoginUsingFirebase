package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.login.databinding.ActivityUpdatePasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UpdatePasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityUpdatePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdatePasswordBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)


        binding.buttonSignOut.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)

            finish()
        }
        binding.buttonUpdatePassword.setOnClickListener {
            val user = auth.currentUser
            val password = binding.editTextPassword.text.toString()

            if (CheckPasswordField()) {

                user?.updatePassword(password)?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Update sucessfully.", Toast.LENGTH_SHORT)
                            .show()

                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.e("error: ", it.exception.toString())
                    }
                }
            }

        }


    }

    private fun CheckPasswordField(): Boolean {
        if (binding.editTextPassword.text.toString() == "") {
            binding.textInputLayoutPasssword.error = "This is required field"
            binding.textInputLayoutPasssword.errorIconDrawable = null
            return false
        }

        if (binding.editTextPassword.length() <= 7) {
            binding.textInputLayoutPasssword.error =
                "Password should be at least 8 characters long "
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



