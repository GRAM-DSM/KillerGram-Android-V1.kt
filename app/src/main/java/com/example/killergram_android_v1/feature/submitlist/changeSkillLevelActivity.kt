package com.example.killergram_android_v1.feature.submitlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityChangeSkillLevelBinding

class ChangeSkillLevelActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityChangeSkillLevelBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnChangeSkillSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val changeSkillLevelToSubmit = Intent(this, SubmitBasketballActivity::class.java)

        when(v?.id) {
            R.id.btn_change_skill_submit -> {
                startActivity(changeSkillLevelToSubmit)
            }
        }
    }
}