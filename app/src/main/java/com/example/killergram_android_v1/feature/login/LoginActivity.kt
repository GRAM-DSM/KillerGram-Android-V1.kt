package com.example.killergram_android_v1.feature.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityLoginBinding
import com.example.killergram_android_v1.feature.signup.InputEmailActivity



class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        binding.tvSignUp.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val loginToInputEmail = Intent(this, InputEmailActivity::class.java)

        when(view?.id) {
            R.id.tv_sign_up -> {
                startActivity(loginToInputEmail)
            }
        }
    }
}