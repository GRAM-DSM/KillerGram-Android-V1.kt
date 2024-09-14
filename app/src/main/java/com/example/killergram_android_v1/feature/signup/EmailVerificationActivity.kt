package com.example.killergram_android_v1.feature.signup

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityEmailVerificationBinding
import com.example.killergram_android_v1.feature.login.LoginActivity

class EmailVerificationActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEmailVerificationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_email_verification)

        binding.btnLogin.setOnClickListener(this)
        binding.leftArrow.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val EmailVerifiactionToSetPassword = Intent(this, SetPasswordActivity::class.java)
        val EmailVerifiactionToInputEmail = Intent(this, InputEmailActivity::class.java)


        when(view?.id) {
            R.id.btn_login -> {
                startActivity(EmailVerifiactionToSetPassword)
            }
            R.id.leftArrow -> {
                startActivity(EmailVerifiactionToInputEmail)
            }
        }
    }
}