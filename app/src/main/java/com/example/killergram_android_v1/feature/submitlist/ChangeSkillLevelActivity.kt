package com.example.killergram_android_v1.feature.submitlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityChangeSkillLevelBinding

class ChangeSkillLevelActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityChangeSkillLevelBinding.inflate(layoutInflater)
    }

    private val submitViewModel by lazy {
        ViewModelProvider(this@ChangeSkillLevelActivity)[SubmitViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        skillViewClickListener()

        binding.btnChangeSkillSubmit.setOnClickListener(this)
        binding.btnSkill1.setOnClickListener(this)
        binding.btnSkill2.setOnClickListener(this)
        binding.btnSkill3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val changeSkillLevelToSubmit = Intent(this, SubmitBasketballActivity::class.java)

        when(v?.id) {
            R.id.btn_change_skill_submit -> {
                startActivity(changeSkillLevelToSubmit)
            }
            R.id.btn_skill_1 -> {
                submitViewModel.onSelectItem(1)
            }
            R.id.btn_skill_2 -> {
                submitViewModel.onSelectItem(2)
            }
            R.id.btn_skill_3 -> {
                submitViewModel.onSelectItem(3)
            }
        }
    }

    private fun skillViewClickListener() {
        submitViewModel.buttonState.observe(this@ChangeSkillLevelActivity) {
            when(it) {
                1 -> {
                    binding.btnSkill1.background = AppCompatResources.getDrawable(this, R.drawable.button_selected)
                    binding.btnSkill2.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill3.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                }
                2 -> {
                    binding.btnSkill1.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill2.background = AppCompatResources.getDrawable(this, R.drawable.button_selected)
                    binding.btnSkill3.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                }
                3 -> {
                    binding.btnSkill1.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill2.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill3.background = AppCompatResources.getDrawable(this, R.drawable.button_selected)
                }
            }
        }
    }
}