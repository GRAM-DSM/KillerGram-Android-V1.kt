package com.example.killergram_android_v1.feature.enterinfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityEnterNameBinding
import com.example.killergram_android_v1.feature.home.HomeActivity

class EnterSkillsActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEnterNameBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val enterNameToHome = Intent(this, HomeActivity::class.java)
        val enterNameToEnterGender = Intent(this, EnterGenderActivity::class.java)


        when(view?.id) {
            R.id.btn_login -> {
                startActivity(enterNameToHome)
            }
            R.id.img_left_arrow -> {
                startActivity(enterNameToEnterGender)
            }
        }
    }
}