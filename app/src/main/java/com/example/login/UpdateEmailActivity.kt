package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login.databinding.ActivityUpdateEmailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UpdateEmailActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding:ActivityUpdateEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateEmailBinding.inflate(layoutInflater)
        auth = Firebase.auth

        setContentView(binding.root)


    }
}