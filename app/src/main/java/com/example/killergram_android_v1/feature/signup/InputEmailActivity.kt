package com.example.killergram_android_v1.feature.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.data.api.ApiProvider
import com.example.killergram_android_v1.data.request.auth.EmailRequest
import com.example.killergram_android_v1.databinding.ActivityInputEmailBinding
import com.example.killergram_android_v1.feature.login.LoginActivity
import com.example.killergram_android_v1.feature.utils.isRegexEmail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputEmailActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityInputEmailBinding.inflate(layoutInflater)
    }
    private var emailFlag = false

    private val retrofit = ApiProvider.getAuthApi()

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

        val email = binding.tieEmail.text.toString()

        when(view?.id) {
            R.id.btn_login -> {
                connectEmailToServer(email)
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

    private fun connectEmailToServer(email: String) {
        val inputEmailToEmailVerification = Intent(this, EmailValidationActivity::class.java)

        retrofit.sendEmail(
            EmailRequest(
                email = email
            )
        ).enqueue(object : Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                when(response.code()) {
                    200 -> {
                        Toast.makeText(baseContext, "이메일 값 성공!", Toast.LENGTH_SHORT).show()
                        Log.d("TEST", response.code().toString())
                        startActivity(inputEmailToEmailVerification)
                    }
                    else -> {

                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("TEST", t.message.toString())
            }
        })
    }
}