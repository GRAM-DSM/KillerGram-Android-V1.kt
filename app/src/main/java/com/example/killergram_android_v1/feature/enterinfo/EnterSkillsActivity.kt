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

class EnterSkillsActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEnterNameBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enter_skills)

        binding.btnLogin.setOnClickListener(this)
        binding.ImVLeftArrow.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val enterNameToEnterGrade = Intent(this, EnterGradeActivity::class.java)
        val enterNameToEnterGender = Intent(this, EnterGenderActivity::class.java)


        when(view?.id) {
            R.id.btn_login -> {
                startActivity(enterNameToEnterGrade)
            }
            R.id.ImV_left_arrow -> {
                startActivity(enterNameToEnterGender)
            }
        }
    }
}