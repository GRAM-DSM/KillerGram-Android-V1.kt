package com.example.killergram_android_v1.feature.enterinfo.enterSkill

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityEnterSkillsBinding
import com.example.killergram_android_v1.feature.enterinfo.endterGender.EnterGenderActivity
import com.example.killergram_android_v1.feature.login.LoginActivity
import com.example.killergram_android_v1.feature.type.Ability

class EnterSkillsActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEnterSkillsBinding.inflate(layoutInflater)
    }

    private val enterSkillsViewModel by lazy {
        ViewModelProvider(this@EnterSkillsActivity)[EnterSkillsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        Log.d("TEST2", this.getSharedPreferences("grade", Context.MODE_PRIVATE).getString("grade", "").toString())
        Log.d("TEST3", this.getSharedPreferences("password", Context.MODE_PRIVATE).getString("password", "").toString())


        binding.btnLogin.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)

        binding.btnSkill1.setOnClickListener(this)
        binding.btnSkill2.setOnClickListener(this)
        binding.btnSkill3.setOnClickListener(this)

        skillViewClickListener()
    }

    override fun onClick(view: View?) {
        val enterNameToLogin = Intent(this, LoginActivity::class.java)
        val enterNameToEnterGender = Intent(this, EnterGenderActivity::class.java)

        when(view?.id) {
            R.id.btn_login -> {
                Toast.makeText(this, "회원가입에 성공하였습니다!", Toast.LENGTH_SHORT).show()
                startActivity(enterNameToLogin)
            }
            R.id.img_left_arrow -> {
                startActivity(enterNameToEnterGender)
            }
            R.id.btn_skill_1 -> {
                setStateCheck(binding.btnSkill1)
            }
            R.id.btn_skill_2 -> {
                setStateCheck(binding.btnSkill2)
            }
            R.id.btn_skill_3 -> {
                setStateCheck(binding.btnSkill3)
            }
        }
    }

    private fun setStateCheck(view: View?) {
        when(view?.id) {
            R.id.btn_skill_1 -> {
                enterSkillsViewModel.onSelectItem(1)
            }
            R.id.btn_skill_2 -> {
                enterSkillsViewModel.onSelectItem(2)
            }
            R.id.btn_skill_3 -> {
                enterSkillsViewModel.onSelectItem(3)
            }
        }
    }

    private fun skillViewClickListener() {
        val pref = this.getSharedPreferences("ability", Context.MODE_PRIVATE)
        val editor = pref.edit()

        enterSkillsViewModel.buttonState.observe(this@EnterSkillsActivity) {
            when(it) {
                1 -> {
                    binding.btnSkill1.background = AppCompatResources.getDrawable(this, R.drawable.button_selected)
                    binding.btnSkill2.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill3.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)

                    editor.putString("ability", Ability.UPPER.toString())
                    editor.apply()
                }
                2 -> {
                    binding.btnSkill1.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill2.background = AppCompatResources.getDrawable(this, R.drawable.button_selected)
                    binding.btnSkill3.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)

                    editor.putString("ability", Ability.MIDDLE.toString())
                    editor.apply()
                }
                3 -> {
                    binding.btnSkill1.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill2.background = AppCompatResources.getDrawable(this, R.drawable.button_unselected)
                    binding.btnSkill3.background = AppCompatResources.getDrawable(this, R.drawable.button_selected)

                    editor.putString("ability", Ability.LOWER.toString())
                    editor.apply()
                }
            }
        }
    }
}