package com.example.killergram_android_v1.feature.submitlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivitySubmitBasketballBinding
import com.example.killergram_android_v1.feature.home.HomeActivity

class SubmitActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivitySubmitBasketballBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.imgLeftArrow.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val submitBasketballToHome = Intent(this, HomeActivity::class.java)

        when(v?.id) {
            R.id.img_left_arrow -> {
                startActivity(submitBasketballToHome)
            }
            R.id.btn_submit -> {
                startActivity(submitBasketballToHome)
            }
        }
    }
}