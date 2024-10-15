package com.example.killergram_android_v1.feature.submitlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivitySubmitBinding
import com.example.killergram_android_v1.feature.home.HomeActivity
import com.example.killergram_android_v1.feature.submitlist.changeSkillLevel.ChangeSkillLevelActivity

class SubmitBasketballActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivitySubmitBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.imgSubmitBack.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
        binding.tvSubmitChangeSkill.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val submitToHome = Intent(this, HomeActivity::class.java)
        val submitToChangeSkillLevel = Intent(this, ChangeSkillLevelActivity::class.java)

        when(v?.id) {
            R.id.img_submit_back -> {
                finish()
            }
            R.id.btn_submit -> {
                startActivity(submitToHome)
            }
            R.id.tv_submit_change_skill -> {
                startActivity(submitToChangeSkillLevel)
            }
        }
    }
}