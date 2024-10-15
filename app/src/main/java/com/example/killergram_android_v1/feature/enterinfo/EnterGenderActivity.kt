package com.example.killergram_android_v1.feature.enterinfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityEnterGenderBinding
import com.example.killergram_android_v1.feature.enterinfo.enterSkill.EnterSkillsActivity

class EnterGenderActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEnterGenderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.imgEnterGenderBack.setOnClickListener(this)
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

            R.id.img_enter_gender_back -> {
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
