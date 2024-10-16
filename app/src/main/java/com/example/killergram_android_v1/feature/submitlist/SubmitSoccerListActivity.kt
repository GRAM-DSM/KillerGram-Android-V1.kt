package com.example.killergram_android_v1.feature.submitlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivitySubmitSoccerListBinding
import com.example.killergram_android_v1.feature.home.HomeActivity

class SubmitSoccerListActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySubmitSoccerListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val submitSoccerToHome = Intent(this, HomeActivity::class.java)

            startActivity(submitSoccerToHome)
        }
    }
}