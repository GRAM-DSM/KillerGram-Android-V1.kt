package com.example.killergram_android_v1.feature.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityInputEmailBinding
import com.example.killergram_android_v1.feature.login.LoginActivity

class InputEmailActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityInputEmailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_input_email)

        binding.btnLogin.setOnClickListener(this)
        binding.leftArrow.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val inputEmailToEmailVerification = Intent(this, EmailVerificationActivity::class.java)
        val inputEmailToLogin = Intent(this, LoginActivity::class.java)

        when(view?.id) {
            R.id.btn_login -> {
                startActivity(inputEmailToEmailVerification)
            }
            R.id.leftArrow -> {
                startActivity(inputEmailToLogin)
            }
        }
    }
}