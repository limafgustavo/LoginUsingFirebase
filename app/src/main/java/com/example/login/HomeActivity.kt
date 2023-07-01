package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.login.databinding.ActivityHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


        binding.buttonSignOut.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)

            finish()
        }

        binding.buttonChangePassword.setOnClickListener {
            val intent = Intent(this, UpdatePasswordActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonChangeEmail.setOnClickListener {
            intent = Intent(this, UpdateEmailActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonDeleteAccount.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Are you absolutely certain?")
                .setPositiveButton("Confirm") { dialog, which ->
                    auth.currentUser?.delete()?.addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this, "Account deleted sucessfully.", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, SignInActivity::class.java))
                            finish()
                        }else{
                            Log.e("Error: ", it.exception.toString())
                        }
                    }

                }
                .setNegativeButton("Cancel", null)
                .show()
        }

    }
}