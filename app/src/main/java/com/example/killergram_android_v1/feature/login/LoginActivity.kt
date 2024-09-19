package com.example.killergram_android_v1.feature.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        isRegexEmail()
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

    private fun isRegexEmail() {
        val email: String = binding.inputEmail.text.toString().trim()
        val emailPattern = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        Log.d("TEST", "함수 실행")

        binding.inputEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("TEST", "onTextChanged 연결")
                if (s?.length!! >= binding.inputEmailLayout.getCounterMaxLength())
                    binding.inputEmailLayout.error = "Max character length is " + binding.inputEmailLayout.getCounterMaxLength()
                else if (!emailPattern) {
                    binding.inputEmailLayout.error = "이메일 형식이 맞지 않습니다!"
                    Log.d("TEST", s.toString() + "이메일 형식이 맞지 않습니다!")
                }
                else
                    binding.inputEmailLayout.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }
}