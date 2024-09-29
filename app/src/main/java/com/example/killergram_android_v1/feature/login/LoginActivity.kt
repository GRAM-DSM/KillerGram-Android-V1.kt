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
import com.example.killergram_android_v1.feature.utils.isRegexEmail
import com.example.killergram_android_v1.feature.utils.isRegexPassword
import java.util.regex.Pattern


open class LoginActivity : AppCompatActivity(), View.OnClickListener {
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
                Toast.makeText(this, if (flagCheck()) "로그인에 성공하였습니다!" else "로그인에 실패하였습니다!", Toast.LENGTH_LONG).show()
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
                            binding.tilEmail.setBoxCornerRadii(5f,5f,5f,5f)
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
                            binding.tilPwd.error = "비밀번호를 입력해주세요"
                        }
                        !isRegexPassword(s.toString()) -> {
                            binding.tilPwd.error = "비밀번호 형식이 맞지 않습니다!"
                        }
                        else -> {
                            binding.tilPwd.error = null
                            passwordFlag = true
                        }
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }



    private fun flagCheck(): Boolean {
        return emailFlag && passwordFlag
    }
}