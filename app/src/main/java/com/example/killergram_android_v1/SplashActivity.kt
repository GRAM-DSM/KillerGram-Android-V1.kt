package com.example.killergram_android_v1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.databinding.ActivitySplashBinding
import com.example.killergram_android_v1.feature.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        moveToLoginActivity()
    }

    private fun moveToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed( {
            startActivity(intent)
        }, 3000)
    }
}