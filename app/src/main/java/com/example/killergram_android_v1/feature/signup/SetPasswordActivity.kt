package com.example.killergram_android_v1.feature.signup

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivitySetPasswordBinding
import com.example.killergram_android_v1.feature.enterinfo.EnterNameActivity

class SetPasswordActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivitySetPasswordBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_set_password)

        binding.btnLogin.setOnClickListener(this)
        binding.leftArrow.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val setPasswordToEnterName = Intent(this, EnterNameActivity::class.java)
        val setPasswordToEmailVerification = Intent(this, EmailVerificationActivity::class.java)

        when(view?.id) {
            R.id.btn_login -> {
                startActivity(setPasswordToEnterName)
            }
            R.id.leftArrow -> {
                startActivity(setPasswordToEmailVerification)
            }
        }
    }
}