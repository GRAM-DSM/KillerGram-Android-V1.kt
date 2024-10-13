package com.example.killergram_android_v1.feature.submitlist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.databinding.ActivitySubmitBasketballBinding

class SubmitBasketballActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySubmitBasketballBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

    }
}