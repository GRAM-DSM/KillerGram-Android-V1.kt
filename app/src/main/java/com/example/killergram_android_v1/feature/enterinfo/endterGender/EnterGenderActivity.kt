package com.example.killergram_android_v1.feature.enterinfo.endterGender

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityEnterGenderBinding
import com.example.killergram_android_v1.feature.enterinfo.EnterGradeActivity
import com.example.killergram_android_v1.feature.enterinfo.enterSkill.EnterSkillsActivity

class EnterGenderActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEnterGenderBinding.inflate(layoutInflater)
    }
    private val enterGenderViewModel by lazy {
        ViewModelProvider(this@EnterGenderActivity)[EnterGenderViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.imgLeftArrow.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
        binding.btnMan.setOnClickListener(this)
        binding.btnWoman.setOnClickListener(this)

        onGenderToggleSelected()
    }

    override fun onClick(v: View?) {
        val enterGenderToEnterSkills = Intent(this, EnterSkillsActivity::class.java)
        val enterGenderToEnterGrade = Intent(this, EnterGradeActivity::class.java)

        when (v?.id) {
            R.id.btn_login -> {
                startActivity(enterGenderToEnterSkills)
            }

            R.id.img_left_arrow -> {
                startActivity(enterGenderToEnterGrade)
            }

            R.id.btn_man -> {
                enterGenderViewModel.onSelectItem(1)
            }

            R.id.btn_woman -> {
                enterGenderViewModel.onSelectItem(2)
            }
        }
    }

    private fun onGenderToggleSelected() {
        enterGenderViewModel.buttonState.observe(this@EnterGenderActivity) {
            when(it) {
                1 -> {
                    binding.btnMan.background = AppCompatResources.getDrawable(this, R.drawable.button_selected)
                    binding.btnWoman.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                }
                2 -> {
                    binding.btnMan.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnWoman.background = AppCompatResources.getDrawable(this, R.drawable.button_selected)
                }
            }
        }
    }
}
