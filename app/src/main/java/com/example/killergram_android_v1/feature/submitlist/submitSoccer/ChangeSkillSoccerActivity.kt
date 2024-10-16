package com.example.killergram_android_v1.feature.submitlist.submitSoccer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityChangeSkillSoccerBinding
import com.example.killergram_android_v1.feature.submitlist.SubmitViewModel

class ChangeSkillSoccerActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityChangeSkillSoccerBinding.inflate(layoutInflater)
    }

    private val submitViewModel by lazy {
        ViewModelProvider(this@ChangeSkillSoccerActivity)[SubmitViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        skillViewClickListener()
        binding.btnSkill1.setOnClickListener(this)
        binding.btnSkill2.setOnClickListener(this)
        binding.btnSkill3.setOnClickListener(this)
        binding.btnChangeSkillSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val changeSkillSoccerToSubmitSoccer = Intent(this, SubmitSoccerListActivity::class.java)

        when(v?.id) {
            R.id.btn_skill_1 -> {
                submitViewModel.onSelectItem(1)
            }
            R.id.btn_skill_2 -> {
                submitViewModel.onSelectItem(2)
            }
            R.id.btn_skill_3 -> {
                submitViewModel.onSelectItem(3)
            }
            R.id.btn_change_skill_submit -> {
                startActivity(changeSkillSoccerToSubmitSoccer)
            }
        }
    }

    private fun skillViewClickListener() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()

        when(pref.getString("SkillLevel", "")) {
            "상" -> {
                submitViewModel.onSelectItem(1)
            }
            "중" -> {
                submitViewModel.onSelectItem(2)
            }
            "하" -> {
                submitViewModel.onSelectItem(3)
            }
            else -> {}
        }

        submitViewModel.buttonState.observe(this) {
            when(it) {
                1 -> {
                    binding.btnSkill1.background = AppCompatResources.getDrawable(this, R.drawable.button_selected)
                    binding.btnSkill2.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill3.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)

                    edit.putString("SkillLevel", "상")
                    edit.apply()
                }
                2 -> {
                    binding.btnSkill1.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill2.background = AppCompatResources.getDrawable(this, R.drawable.button_selected)
                    binding.btnSkill3.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)

                    edit.putString("SkillLevel", "중")
                    edit.apply()
                }
                3 -> {
                    binding.btnSkill1.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill2.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill3.background = AppCompatResources.getDrawable(this, R.drawable.button_selected)

                    edit.putString("SkillLevel", "하")
                    edit.apply()
                    Log.d("TEST", pref.getString("SKillLevel", " ").toString())
                }
            }
        }
    }
}