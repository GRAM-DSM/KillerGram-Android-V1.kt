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

class InputEmailActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityInputEmailBinding.inflate(layoutInflater)
    }
    private var emailFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_input_email)

        onEmailListener()

        binding.btnLogin.setOnClickListener(this)
        binding.ImVLeftArrow.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val inputEmailToEmailVerification = Intent(this, EmailValidationActivity::class.java)
        val inputEmailToLogin = Intent(this, LoginActivity::class.java)

        when(view?.id) {
            R.id.btn_login -> {
                startActivity(inputEmailToEmailVerification)
            }
            R.id.ImV_left_arrow -> {
                startActivity(inputEmailToLogin)
            }
        }
    }

    private fun onEmailListener() {
        binding.TIEEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s != null) {
                    when {
                        s.isEmpty() -> {
                            binding.TILEmail.boxStrokeWidth = 2
                            binding.TILEmail.setBoxCornerRadii(5f,5f,5f,5f)
                            binding.TILEmail.error = "이메일을 입력해주세요"
                        }
                        !isRegexEmail(s.toString()) -> {
                            binding.TILEmail.error = "이메일 형식이 맞지 않습니다!"
                        }
                        else -> {
                            binding.TILEmail.error = null
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

    private fun isRegexEmail(email: String): Boolean {
        return email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$".toRegex())
    }

    private fun flagCheck(): Boolean {
        return emailFlag
    }
}