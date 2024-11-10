package com.example.killergram_android_v1.feature.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.data.api.ApiProvider
import com.example.killergram_android_v1.data.request.auth.signup.SetPasswordRequest
import com.example.killergram_android_v1.databinding.ActivitySetPasswordBinding
import com.example.killergram_android_v1.feature.enterinfo.EnterNameActivity
import com.example.killergram_android_v1.feature.utils.isRegexPassword
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class SetPasswordActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivitySetPasswordBinding.inflate(layoutInflater)
    }
    private var passwordFlag = false
    private var passwordCheckFlag = false

    private val retrofit = ApiProvider.getAuthApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)

        onPasswordListener()
        onPasswordCheckListener()
    }

    override fun onClick(view: View?) {
        val setPasswordToEmailVerification = Intent(this, EmailValidationActivity::class.java)

        when(view?.id) {
            R.id.btn_login -> {
                passwordSendToServer()
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
                        binding.tiePwd.text.toString() != s.toString() -> {
                            binding.tilPwdCheck.error = "비밀번호가 일치하지 않습니다."
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

    private fun flagCheck(): Boolean {
        return passwordFlag && passwordCheckFlag
    }

    private fun passwordSendToServer() {
        val setPasswordToEnterName = Intent(this, EnterNameActivity::class.java)
        retrofit.setPassword(
            SetPasswordRequest(
                "", // 계정 id 에 적합한 값 대입
                binding.tiePwd.text.toString()
            )
        ).enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when(response.code()) {
                    200 -> {
                        startActivity(setPasswordToEnterName)
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

        })
    }
}