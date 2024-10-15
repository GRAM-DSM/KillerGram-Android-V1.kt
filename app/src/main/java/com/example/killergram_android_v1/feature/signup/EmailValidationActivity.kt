package com.example.killergram_android_v1.feature.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityEmailValidationBinding
import java.util.Objects
import java.util.Timer
import java.util.TimerTask

class EmailValidationActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEmailValidationBinding.inflate(layoutInflater)
    }
    private var time = 0
    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)
        binding.tvEmailReverification.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val emailValidationToSetPassword = Intent(this, SetPasswordActivity::class.java)
        val emailValidationToInputEmail = Intent(this, InputEmailActivity::class.java)

        when(view?.id) {
            R.id.btn_login -> {
                startActivity(emailValidationToSetPassword)
            }
            R.id.img_left_arrow -> {
                startActivity(emailValidationToInputEmail)
            }
            R.id.tv_email_reverification -> {
                timer.schedule(getTaskTimer(), 1000, 1000)
            }
        }
    }

    private fun getTaskTimer(): TimerTask {
        binding.tvTitleTimer.text = "05:00"
        time = 300

        return object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    if (time == 0) {
                        timer.cancel()
                        binding.tilEmailVerification.error = "제한시간 5분을 초과하였습니다."
                    }
                    time -= 1
                    val min = time / 60
                    val sec = time % 60
                    binding.tvTitleTimer.text = String.format("%02d:%02d", min, sec)
                }
            }
        }
    }
}