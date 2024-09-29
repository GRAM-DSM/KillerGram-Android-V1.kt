package com.example.killergram_android_v1.feature.enterinfo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.View
import android.widget.CompoundButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.CompoundButtonCompat
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityEnterGenderBinding

class EnterGenderActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEnterGenderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enter_gender)

        binding.imgLeftArrow.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)

        binding.btnMan.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val enterGenderToEnterSkills = Intent(this, EnterSkillsActivity::class.java)
        val enterGenderToEnterGrade = Intent(this, EnterGradeActivity::class.java)

        var selectW = false
        var selectM = false

        when (v?.id) {
            R.id.btn_login -> {
                startActivity(enterGenderToEnterSkills)
            }

            R.id.img_left_arrow -> {
                startActivity(enterGenderToEnterGrade)
            }

            R.id.btn_man -> {
                onWomanToggleSelected()
            }

            R.id.btn_woman -> {
                onManToggleSelected()
            }
        }
    }

    private fun onWomanToggleSelected() {
    }

    private fun onManToggleSelected() {

    }
}
