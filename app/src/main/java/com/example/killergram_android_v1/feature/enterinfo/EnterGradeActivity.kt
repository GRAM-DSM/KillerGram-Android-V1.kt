package com.example.killergram_android_v1.feature.enterinfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityEnterGradeBinding

class EnterGradeActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEnterGradeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val enterGradeToEnterGender = Intent(this, EnterGenderActivity::class.java)
        val enterGradeToEnterName = Intent(this, EnterNameActivity::class.java)


        when(v?.id) {
            R.id.btn_login -> {
                startActivity(enterGradeToEnterGender)
            }
            R.id.img_left_arrow -> {
                startActivity(enterGradeToEnterName)
            }
        }
    }
}