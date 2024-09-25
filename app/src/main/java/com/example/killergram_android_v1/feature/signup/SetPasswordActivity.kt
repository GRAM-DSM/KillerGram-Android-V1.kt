package com.example.killergram_android_v1.feature.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivitySetPasswordBinding
import com.example.killergram_android_v1.feature.enterinfo.EnterNameActivity

class SetPasswordActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivitySetPasswordBinding.inflate(layoutInflater)
    }
    private var passwordFlag = false
    private var passwordCheckFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_set_password)

        binding.btnLogin.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)

        onPasswordListener()
        onPasswordCheckListener()
    }

    override fun onClick(view: View?) {
        val setPasswordToEnterName = Intent(this, EnterNameActivity::class.java)
        val setPasswordToEmailVerification = Intent(this, EmailValidationActivity::class.java)

        when(view?.id) {
            R.id.btn_login -> {
                startActivity(setPasswordToEnterName)
            }
            R.id.img_left_arrow -> {
                startActivity(setPasswordToEmailVerification)
            }
        }
    }

    private fun onPasswordListener() {
        binding.tiePwd.addTextChangedListener(object : TextWatcher {
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

    private fun onPasswordCheckListener() {
        binding.tiePwdCheck.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s != null) {
                    when {
                        s.isEmpty() -> {
                            binding.tilPwdCheck.error = "비밀번호를 입력해주세요"
                        }
                        !isRegexPassword(s.toString()) -> {
                            binding.tilPwdCheck.error = "비밀번호 형식이 맞지 않습니다!"
                        }
                        else -> {
                            binding.tilPwdCheck.error = null
                            passwordCheckFlag = true
                        }
                    }
                    flagCheck()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun isRegexPassword(password: String): Boolean {
        // 8~16글자, 대문자 1개, 소문자 1개, 숫자 1개
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,16}$".toRegex())
    }


    private fun flagCheck(): Boolean {
        return passwordFlag && passwordCheckFlag
    }
}