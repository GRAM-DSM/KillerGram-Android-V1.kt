package com.example.killergram_android_v1.feature.submitlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivitySubmitBinding
import com.example.killergram_android_v1.feature.home.HomeActivity
import com.example.killergram_android_v1.feature.home.HomeViewModel
import com.example.killergram_android_v1.feature.recyclerView.home.HomeAdapter
import com.example.killergram_android_v1.feature.recyclerView.home.data.Sport

class SubmitBasketballActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivitySubmitBinding.inflate(layoutInflater)
    }
    private val submitViewModel by lazy {
        ViewModelProvider(this)[SubmitViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.imgSubmitBack.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
        binding.tvSubmitChangeSkill.setOnClickListener(this)

        observeChangeText()
    }

    override fun onClick(v: View?) {
        val submitToHome = Intent(this, HomeActivity::class.java)
        val submitToChangeSkillLevel = Intent(this, ChangeSkillLevelActivity::class.java)


        submitToHome.putExtra("sportName", "농구")
        submitToHome.putExtra("personnel", 14)
        submitToHome.putExtra("participate", 2  )
        submitToHome.putExtra("isEnd", false)

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

    private fun observeChangeText() {
        val pref = getSharedPreferences("pref", 0)

        binding.tvSubmitSkillText.text = pref.getString("SkillLevel", "")
    }
}