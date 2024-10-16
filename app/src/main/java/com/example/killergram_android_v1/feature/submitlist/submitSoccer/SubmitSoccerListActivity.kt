package com.example.killergram_android_v1.feature.submitlist.submitSoccer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivitySubmitSoccerListBinding
import com.example.killergram_android_v1.feature.home.HomeActivity
import com.example.killergram_android_v1.feature.submitlist.ChangeSkillLevelActivity
import com.example.killergram_android_v1.feature.submitlist.SubmitViewModel

class SubmitSoccerListActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySubmitSoccerListBinding.inflate(layoutInflater)
    }
    private val submitViewModel by lazy {
        ViewModelProvider(this)[SubmitViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val submitSoccerToHome = Intent(this, HomeActivity::class.java)

            startActivity(submitSoccerToHome)
        }
        binding.tvSubmitChangeSkill.setOnClickListener {
            val submitToChangeSkillSoccer = Intent(this, ChangeSkillSoccerActivity::class.java)
            startActivity(submitToChangeSkillSoccer)
        }
        binding.btnSubmit.setOnClickListener {
            val submitToHome = Intent(this, HomeActivity::class.java)

            startActivity(submitToHome)
        }
        observeChangeText()
    }
    private fun observeChangeText() {
        val pref = getSharedPreferences("pref", 0)

        binding.tvSubmitSkillText.text = pref.getString("SkillLevel", "")
    }
}