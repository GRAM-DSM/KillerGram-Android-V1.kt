package com.example.killergram_android_v1.feature.enterinfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ActivityEnterNameBinding
import com.example.killergram_android_v1.feature.signup.SetPasswordActivity

class EnterNameActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityEnterNameBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.imgLeftArrow.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val enterNameToEnterGrade = Intent(this, EnterGradeActivity::class.java)
        val enterNameToSetPassword = Intent(this, SetPasswordActivity::class.java)


        when(view?.id) {
            R.id.btn_login -> {
                startActivity(enterNameToEnterGrade)
            }
            R.id.img_left_arrow -> {
                startActivity(enterNameToSetPassword)
            }
        }
    }
}