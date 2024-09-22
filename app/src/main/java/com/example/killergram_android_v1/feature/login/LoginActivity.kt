package com.example.killergram_android_v1.feature.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityLoginBinding
import com.example.killergram_android_v1.feature.signup.InputEmailActivity
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private var emailFlag = false
    private var passwordFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        onEmailListener()
        onPasswordListener()
        binding.tvSignUp.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        val loginToInputEmail = Intent(this, InputEmailActivity::class.java)

        when(view?.id) {
            R.id.tv_sign_up -> {
                startActivity(loginToInputEmail)
            }
            R.id.btn_login -> {
                if (flagCheck()) {
                    Toast.makeText(this, "로그인에 성공하였습니다!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "로그인에 실패하였습니다!", Toast.LENGTH_LONG).show()
                }
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
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun onPasswordListener() {
        binding.LoginTIEPwd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s != null) {
                    when {
                        s.isEmpty() -> {
                            binding.TILPwd.error = "비밀번호를 입력해주세요"
                        }
                        !isRegexPassword(s.toString()) -> {
                            binding.TILPwd.error = "비밀번호 형식이 맞지 않습니다!"
                        }
                        else -> {
                            binding.TILPwd.error = null
                            passwordFlag = true
                        }
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun isRegexEmail(email: String): Boolean {
        return email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$".toRegex())
    }

    private fun isRegexPassword(password: String): Boolean {
        // 8~16글자, 대문자 1개, 소문자 1개, 숫자 1개
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,16}$".toRegex())
    }

    private fun flagCheck(): Boolean {
        return emailFlag && passwordFlag
    }
}