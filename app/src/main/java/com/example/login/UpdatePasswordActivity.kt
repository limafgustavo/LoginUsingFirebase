package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class UpdatePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)
    }

    private fun CheckAllField(): Boolean {
        return true
    }
}