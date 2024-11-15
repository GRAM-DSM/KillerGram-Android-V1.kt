package com.example.killergram_android_v1.feature.login

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.FirebaseMessagingService
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.data.api.ApiProvider
import com.example.killergram_android_v1.data.request.auth.login.LoginRequest
import com.example.killergram_android_v1.data.response.auth.login.LoginResponse
import com.example.killergram_android_v1.databinding.ActivityLoginBinding
import com.example.killergram_android_v1.feature.home.HomeActivity
import com.example.killergram_android_v1.feature.signup.InputEmailActivity
import com.example.killergram_android_v1.feature.utils.isRegexEmail
import com.example.killergram_android_v1.feature.utils.isRegexPassword
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.messaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val retrofit = ApiProvider.getAuthApi()

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

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            val token = task.result
            val pref = this.getSharedPreferences("device_token", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("device_token", token)
            editor.apply()
            Log.d("TEST22", token)
        })
    }

    override fun onClick(view: View?) {
        val loginToInputEmail = Intent(this, InputEmailActivity::class.java)

        when(view?.id) {
            R.id.tv_sign_up -> {
                startActivity(loginToInputEmail)
            }
            R.id.btn_login -> {
                if (flagCheck()) {
                    connectLoginToServer()
                } else {
                    binding.tilPwd.error = " "
                    binding.tilEmail.error = " "
                }
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

    private fun connectLoginToServer() {
        val loginToHome = Intent(this, HomeActivity::class.java)
        loginToHome.setFlags(FLAG_ACTIVITY_NEW_TASK)
        val pref = this.getSharedPreferences("token", Context.MODE_PRIVATE)
        val editor = pref.edit()


        retrofit.login(
            LoginRequest(
                accountId = binding.tieEmail.text.toString(),
                password = binding.LoginTIEPwd.text.toString(),
            )
        ).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                when(response.code()) {
                    200 -> {
                        startActivity(loginToHome)
                        editor.putString("access_token", response.body()?.accessToken)
                        editor.putString("refresh_token", response.body()?.refreshToken)
                        editor.apply()
                    }
                    else -> {
                        Log.d("TEST", response.code().toString())
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

            }
        })
    }
}