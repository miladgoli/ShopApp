package com.example.shopapp.view.resetpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopapp.R
import com.example.shopapp.view.signin.SigninActivity

class ResetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)
    }

    override fun onBackPressed() {
        val intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)
        finish()
    }
}