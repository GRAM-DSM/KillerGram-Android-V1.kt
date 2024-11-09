package com.example.killergram_android_v1.feature.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.data.api.ApiProvider
import com.example.killergram_android_v1.data.request.auth.signup.VerifyEmailRequest
import com.example.killergram_android_v1.data.response.auth.signup.VerifyEmailResponse
import com.example.killergram_android_v1.databinding.ActivityEmailValidationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import java.util.TimerTask

class EmailValidationActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEmailValidationBinding.inflate(layoutInflater)
    }
    private var time = 0
    private val timer = Timer()

    private val retrofit = ApiProvider.getAuthApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        timer.schedule(getTaskTimer(), 1000, 1000)

        binding.btnLogin.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)
        binding.tvEmailReverification.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val emailValidationToInputEmail = Intent(this, InputEmailActivity::class.java)

        when(view?.id) {
            R.id.btn_login -> {
                verifyEmailCodeToServer()
            }
            R.id.img_left_arrow -> {
                startActivity(emailValidationToInputEmail)
            }
            R.id.tv_email_reverification -> {
                getTaskTimer().scheduledExecutionTime()
                timer.schedule(getTaskTimer(), 1000, 1000)
            }
        }
    }

    private fun getTaskTimer(): TimerTask {
        binding.tvTitleTimer.text = "05:00"
        time = 10

        return object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    if (time != 0) {
                        Log.d("TEST", time.toString())
                        binding.tilEmailVerification.error = null
                        time -= 1
                        val min = time / 60
                        val sec = time % 60
                        binding.tvTitleTimer.text = String.format("%02d:%02d", min, sec)
                    } else {
                        timer.cancel()
                        binding.tilEmailVerification.error = "제한시간 5분을 초과하였습니다."
                    }
                }
            }
        }
    }

    private fun verifyEmailCodeToServer() {
        val emailCode = binding.tieEmailVerification.text.toString()
        val email = intent.getStringExtra("killerGram")!!


        retrofit.verifyEmail(
            VerifyEmailRequest(
                email = email,
                emailCode = emailCode,
            )
        ).enqueue(object : Callback<VerifyEmailResponse> {
            override fun onResponse(
                call: Call<VerifyEmailResponse>,
                response: Response<VerifyEmailResponse>
            ) {

            }

            override fun onFailure(call: Call<VerifyEmailResponse>, t: Throwable) {

            }
        })
    }
}