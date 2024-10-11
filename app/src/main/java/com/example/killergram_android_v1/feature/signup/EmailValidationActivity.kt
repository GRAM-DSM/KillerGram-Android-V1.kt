package com.example.killergram_android_v1.feature.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityEmailValidationBinding

class EmailValidationActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEmailValidationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val emailValidationToSetPassword = Intent(this, SetPasswordActivity::class.java)
        val emailValidationToInputEmail = Intent(this, InputEmailActivity::class.java)

        when(view?.id) {
            R.id.btn_login -> {
                startActivity(emailValidationToSetPassword)
            }
            R.id.img_left_arrow -> {
                startActivity(emailValidationToInputEmail)
            }
        }
    }
}