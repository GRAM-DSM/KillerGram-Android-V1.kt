package com.example.killergram_android_v1.feature.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityInputEmailBinding
import com.example.killergram_android_v1.feature.login.LoginActivity
import com.example.killergram_android_v1.feature.utils.isRegexEmail

class InputEmailActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityInputEmailBinding.inflate(layoutInflater)
    }
    private var emailFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        onEmailListener()

        binding.btnLogin.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val inputEmailToEmailVerification = Intent(this, EmailValidationActivity::class.java)
        val inputEmailToLogin = Intent(this, LoginActivity::class.java)

        when(view?.id) {
            R.id.btn_login -> {
                startActivity(inputEmailToEmailVerification)
            }
            R.id.img_left_arrow -> {
                startActivity(inputEmailToLogin)
            }
        }
    }

    private fun onEmailListener() {
        binding.tieEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s != null) {
                    when {
                        s.isEmpty() -> {
                            binding.tilEmail.boxStrokeWidth = 2
                            binding.tilEmail.error = "이메일을 입력해주세요"
                        }
                        !isRegexEmail(s.toString()) -> {
                            binding.tilEmail.error = "이메일 형식이 맞지 않습니다!"
                        }
                        else -> {
                            binding.tilEmail.error = null
                            emailFlag = true
                        }
                    }
                    flagCheck()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }



    private fun flagCheck(): Boolean {
        return emailFlag
    }
}